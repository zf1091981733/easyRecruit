package taru.easyrecruit.api.common.shiro;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import taru.easyrecruit.api.common.dict.TokenValue;
import taru.easyrecruit.api.common.utils.JwtUtil;
import taru.easyrecruit.api.common.utils.Query;
import taru.easyrecruit.api.common.utils.RedisOperator;
import taru.easyrecruit.api.dao.entity.UserEntity;
import taru.easyrecruit.api.service.UserService;

@Slf4j
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisOperator redis;

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("shiro授权--------");
        return null;
    }

    //认证
    //这个token就是从过滤器中传入的jwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("shiro认证----------");
        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new UnknownAccountException("Token 不允许为空！");
        }

        //判断token合法
        JwtUtil jwtUtil = new JwtUtil(TokenValue.SALT);
        try {
            jwtUtil.isVerify(jwt);
        }catch (JWTDecodeException e){
            throw new AuthenticationException("认证失败,token非法或过期！");
        }
        //下面是验证这个user是否是真实存在的
        Integer userId = (Integer) jwtUtil.decode(jwt).get("userId");//判断数据库中username是否存在
        UserEntity user = userService.getById(userId);
        if (user == null){
            throw new UnknownAccountException("用户不存在！");
        }
        //验证服务器token是否过期
        String redisToken = redis.get(user.getUserUuid());
        if (StringUtils.isBlank(redisToken)|| !redisToken.equals(jwt)){
            throw new AuthenticationException("token已过期，请重新登陆！");
        }
        log.info("{}在使用token登录",user.getUserName());
        return new SimpleAuthenticationInfo(jwt,jwt,"JwtRealm");
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名
    }

}