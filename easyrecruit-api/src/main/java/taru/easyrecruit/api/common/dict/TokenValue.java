package taru.easyrecruit.api.common.dict;

public interface TokenValue {
    public static String TOKEN="token";

    public static String TOKEN_NAME="Authorization";

    public static long TOKEN_TIME= 12*60*60*1000;   //token有效时间

    public static long TOKEN_REDIS_TIME=12*60*60;   //redis中token过期时间

    public static String SALT="4fdc56cb0";
}
