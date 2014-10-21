package com.devuger.front;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class Application {

  public static void main(String[] args) {
    System.out.println("Devuger Server Start");
  }
}