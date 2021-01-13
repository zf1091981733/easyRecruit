package taru.easyrecruit.api.common.utils;

import taru.easyrecruit.api.common.dict.TokenValue;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    //从token得到userId
    public static Integer getUserId(HttpServletRequest request){
        String jwt = request.getHeader(TokenValue.TOKEN_NAME);
        JwtUtil jwtUtil = new JwtUtil(TokenValue.SALT);
        return (Integer) jwtUtil.decode(jwt).get(TokenValue.ChaimList.userId.toString());
    }
}
