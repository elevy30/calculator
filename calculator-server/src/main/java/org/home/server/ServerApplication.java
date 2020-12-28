package org.home.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Scanner;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        //listenForInput();
    }


    private static void listenForInput() {
        try (Scanner keyboard = new Scanner(System.in)) {
            System.out.println("Press any key [ENTER] to stop operation");
            String input = keyboard.nextLine();
            if (input != null) {
                System.exit(0);
            }
        }
    }


}
