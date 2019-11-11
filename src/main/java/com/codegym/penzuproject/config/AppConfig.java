package com.codegym.penzuproject.config;

import com.codegym.penzuproject.service.IDiaryService;
import com.codegym.penzuproject.service.impl.DiaryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public IDiaryService diaryService(){
        return new DiaryServiceImpl();
    }
//    @Bean
//    FileStorageService fileStorageService(){
//        return new FileStorageService();
//    }
}
