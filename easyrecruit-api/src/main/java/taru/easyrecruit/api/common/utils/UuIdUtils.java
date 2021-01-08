package taru.easyrecruit.api.common.utils;

import java.util.UUID;

public class UuIdUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
