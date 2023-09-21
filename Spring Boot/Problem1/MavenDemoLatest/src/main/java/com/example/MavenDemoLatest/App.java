package com.example.MavenDemoLatest;

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
            File file = new File(
                    "/Users/sivasai/Documents/Spring Boot/MavenDemoLatest/src/main/java/com/example/MavenDemoLatest/index.html");
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
        // return """

        // <html>
        // <head>
        // <title>Basic Form Example</title>
        // </head>
        // <body>
        // <h2>Contact Us</h2>
        // <form action="/submit" method="post">
        // <!-- Name field -->
        // <label for="name">Name:</label>
        // <input type="text" id="name" name="name" required>
        // <br>

        // <!-- Email field -->
        // <label for="email">Email:</label>
        // <input type="email" id="email" name="email" required>
        // <br>

        // <!-- Message field -->
        // <label for="message">Message:</label>
        // <textarea id="message" name="message" rows="4" cols="50" required></textarea>
        // <br>

        // <!-- Submit button -->
        // <input type="submit" value="Submit">
        // </form>
        // </body>
        // </html>
        // """;

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
