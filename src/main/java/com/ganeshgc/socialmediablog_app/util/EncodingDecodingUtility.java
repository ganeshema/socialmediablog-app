package com.ganeshgc.socialmediablog_app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodingDecodingUtility {
    public static void main(String[] args) {
        //using this just to encode the password
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Ganesh ::  "+ encoder.encode("ganesh@2052"));
        System.out.println("Ema :: "+ encoder.encode("ema@2053"));
    }
}
