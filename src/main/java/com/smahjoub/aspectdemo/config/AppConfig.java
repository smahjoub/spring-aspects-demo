package com.smahjoub.aspectdemo.config;

import com.smahjoub.aspectdemo.aspect.PatternCheckAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public PatternCheckAspect getPatternCheckAspect(){
        return new PatternCheckAspect();
    }
}