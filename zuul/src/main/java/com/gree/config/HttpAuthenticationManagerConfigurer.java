package com.gree.config;

import com.gree.feign.AuthTokenApi;
import com.gree.util.DefaultHttpTokenExtractor;
import com.gree.util.HttpTokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class HttpAuthenticationManagerConfigurer {

    @Autowired
    private AuthTokenApi authTokenApi;


    @Bean
    HttpTokenExtractor httpTokenExtractor(){
        return new DefaultHttpTokenExtractor();
    }


    @Bean
    public HttpAuthenticationManager httpAuthenticationManager(){
        return  new DefaultHttpAuthenticationManager(httpTokenExtractor(),authTokenApi);
    }
}
