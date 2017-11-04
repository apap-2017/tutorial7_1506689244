package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.example"})
public class Tutorial07Producer
{

    public static void main (String[] args)
    {
        SpringApplication.run (Tutorial07Producer.class, args);
    }
}
