package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("비밀번호 입력 : ");
        String rawPassword = scanner.nextLine();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("입력된 비밀번호 : "+rawPassword);
        System.out.println("암호화된 비밀번호 : "+encodedPassword);
    }
}
