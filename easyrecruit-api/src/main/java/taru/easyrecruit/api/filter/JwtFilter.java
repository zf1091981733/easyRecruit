package taru.easyrecruit.api.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.AccessControlFilter;
import taru.easyrecruit.api.common.dict.TokenValue;
import taru.easyrecruit.api.common.shiro.JwtToken;
import taru.easyrecruit.api.common.utils.R;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 自定义一个Filter，用来拦截所有的请求判断是否携带Token
 * isAccessAllowed()判断是否携带了有效的JwtToken
 * onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问
 * */
@Slf4j
public class JwtFilter extends AccessControlFilter {
    /*
     * 1. 返回true，shiro就直接允许访问url
     * 2. 返回false，shiro才会根据onAccessDenied的方法的返回值决定是否允许访问url
     * */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.warn("isAccessAllowed 方法被调用");
        //这里先让它始终返回false来使用onAccessDenied()方法
        return false;
    }

    /**
     * 返回结果为true表明登录通过
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.warn("onAccessDenied 方法被调用");
        //这个地方和前端约定，要求前端将jwtToken放在请求的Header部分

        //所以以后发起请求的时候就需要在Header中放一个Authorization，值就是对应的Token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader(TokenValue.TOKEN_NAME);
        log.info("请求的 Header 中藏有 jwtToken {}", jwt);
        JwtToken jwtToken = new JwtToken(jwt);
        /*
         * 下面就是固定写法
         * */
        try {
            // 委托 realm 进行登录认证
            //所以这个地方最终还是调用JwtRealm进行的认证
            getSubject(servletRequest, servletResponse).login(jwtToken);
            //也就是subject.login(token)
        }catch (UnknownAccountException e) {
            log.error(e.getMessage());
            onLoginFail(servletResponse,e.getMessage());
            //调用下面的方法向客户端返回错误信息
            return false;
        }catch (AuthenticationException e) {
            log.error(e.getMessage());
            onLoginFail(servletResponse,e.getMessage());
            //调用下面的方法向客户端返回错误信息
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            onLoginFail(servletResponse,e.getMessage());
            //调用下面的方法向客户端返回错误信息
            return false;
        }
        log.info("shiro认证成功token:{}",jwt);
        return true;
        //执行方法中没有抛出异常就表示登录成功
    }

    //登录失败时默认返回 401 状态码
    private void onLoginFail(ServletResponse response,String message) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
        //响应
        String json = JSON.toJSONString(R.error(HttpStatus.SC_UNAUTHORIZED, message));
        httpResponse.getWriter().write(json);
    }
}
