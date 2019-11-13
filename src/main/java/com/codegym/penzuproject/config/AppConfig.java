package com.codegym.penzuproject.config;

<<<<<<< HEAD
import com.codegym.penzuproject.service.IDiaryService;
import com.codegym.penzuproject.service.impl.DiaryServiceImpl;
=======
import com.codegym.penzuproject.service.ITagService;
import com.codegym.penzuproject.service.impl.TagServiceImpl;
>>>>>>> 73ff89689c22dc36f87e7057593be04481cdad1d
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
<<<<<<< HEAD
    @Bean
    public IDiaryService diaryService(){
        return new DiaryServiceImpl();
    }
//    @Bean
//    FileStorageService fileStorageService(){
//        return new FileStorageService();
//    }
=======

    @Bean
    public ITagService tagService(){
        return new TagServiceImpl();
    }
>>>>>>> 73ff89689c22dc36f87e7057593be04481cdad1d
}
