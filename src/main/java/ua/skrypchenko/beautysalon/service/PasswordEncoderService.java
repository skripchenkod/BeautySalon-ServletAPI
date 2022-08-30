package ua.skrypchenko.beautysalon.service;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.exeption.PasswordEncodeException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderService {
    private static final Logger LOGGER = LogManager.getLogger(PasswordEncoderService.class);

    public String encode(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("Password encode error");
            throw new PasswordEncodeException("Password encode error");
        }
    }
}
