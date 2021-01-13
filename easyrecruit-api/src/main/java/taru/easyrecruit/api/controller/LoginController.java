package taru.easyrecruit.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taru.easyrecruit.api.common.dict.TokenValue;
import taru.easyrecruit.api.common.dict.TokenValue.ChaimList;
import taru.easyrecruit.api.common.utils.JwtUtil;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.RedisOperator;
import taru.easyrecruit.api.dao.entity.UserEntity;
import taru.easyrecruit.api.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisOperator redis;

    @RequestMapping("/login")
    public R login(@RequestBody UserEntity loginUser){
        String username = loginUser.getUserName();
        String password = loginUser.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return R.error("用户名或密码为空");
        }
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("user_name", username));
        if (user == null||!password.equals(user.getPassword())){
            return R.error("用户名或密码错误,请重新登陆");
        }
        //设置token
        JwtUtil jwtUtil = new JwtUtil(TokenValue.SALT);
        Map<String, Object> chaim = new HashMap<>(); //荷载
        chaim.put(ChaimList.userId.toString(),user.getUserId());
        chaim.put(ChaimList.userName.toString(),user.getUserName());
        chaim.put(ChaimList.creatTime.toString(),new Date(System.currentTimeMillis()));
        String jwtToken = jwtUtil.encode(username, TokenValue.TOKEN_TIME, chaim);
        //redis设置token过期策略
        redis.set(user.getUserUuid(),jwtToken,TokenValue.TOKEN_REDIS_TIME);

        //响应数据
        Map<String,Object> data = new HashMap();
        data.put("userId",user.getUserId());
        data.put("userUuid",user.getUserUuid());
        data.put("userName",user.getUserName());
        data.put("loginTime",new Date(System.currentTimeMillis()));
        return R.ok("登陆成功").put("token", jwtToken).put("data",data);
    }

    @RequestMapping("/logout")
    public R logout(){

        return R.ok("退出登陆成功");
    }

}
