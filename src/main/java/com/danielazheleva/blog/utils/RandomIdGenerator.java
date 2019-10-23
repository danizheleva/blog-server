package com.danielazheleva.blog.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class RandomIdGenerator {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "012456789abcdefghijklmnopqrstuvwxyzabcdeghijklmnopqrstuvwxyz";

    public String generateDayId(Integer length){
        return generateRandomString(length);
    }

    private String generateRandomString(Integer length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
}
