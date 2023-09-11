package com.shuaiwu.oauth2.utils;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

public class SecretUtil {

    private static RandomValueStringGenerator generator = new RandomValueStringGenerator(32);

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String generateClientId(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateCliendSecret(){
        return generator.generate();
    }

    public static String encode(String password) {
        return encoder.encode(password);
    }
}
