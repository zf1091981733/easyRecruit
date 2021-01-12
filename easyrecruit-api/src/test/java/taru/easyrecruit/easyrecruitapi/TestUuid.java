package taru.easyrecruit.easyrecruitapi;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import taru.easyrecruit.api.common.utils.JwtUtil;
import taru.easyrecruit.api.common.utils.UuIdUtils;

import java.util.HashMap;
import java.util.Map;

public class TestUuid {

    @Test
    public void testToken(){
        String uuid = UuIdUtils.getUUID();
        JwtUtil jwtUtil = new JwtUtil(uuid);
        //生成token
        Map<String, Object> chaim = new HashMap<>(); //荷载
        chaim.put("userId",1);
        chaim.put("userName","zhoufeng");
        String token = jwtUtil.encode("zhoufeng", 12 * 60 * 60 * 1000, chaim);
        System.out.println("token:"+token);
        //token解码
        System.out.println("token解码-----------");
        Claims decode = jwtUtil.decode(token);
        System.out.println("userId:"+decode.get("userId"));
        System.out.println("userName:"+decode.get("userName"));

    }
    @Test
    public void getUuid(){
        System.out.println(UuIdUtils.getUUID());
    }
}
