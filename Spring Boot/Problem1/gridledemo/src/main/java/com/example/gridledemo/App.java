package com.example.gridledemo;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App {
    
    @GetMapping("/form")
    public String form() throws IOException {
        byte[] data;
        try {
            File file = new File("/Users/sivasai/Documents/Spring Boot/Problem1/MavenDemoLatest/src/main/java/com/example/MavenDemoLatest/index.html");
            FileInputStream fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String str = new String(data, "UTF-8");
            System.out.println(str);

            return str;
        } catch (IOException e) {
            return "no file found" + e;
        }

    }

    @GetMapping("/submit")
    public String submit() {
        return "Thank you for submitting";

    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to MAven Project @LAtest";

    }

}
