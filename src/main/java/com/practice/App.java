package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@EnableWebMvc           //开启springmvc支持
@SpringBootApplication
public class App extends WebMvcConfigurerAdapter
{
	 public static void main(String[] args) {
	        SpringApplication.run(App.class, args);
	    }

}
