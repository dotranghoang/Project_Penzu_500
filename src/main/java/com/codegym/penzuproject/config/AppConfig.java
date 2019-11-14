package com.codegym.penzuproject.config;

import com.codegym.penzuproject.service.IDiaryService;
import com.codegym.penzuproject.service.IRoleService;
import com.codegym.penzuproject.service.ITagService;
import com.codegym.penzuproject.service.IUserService;
import com.codegym.penzuproject.service.Impl.DiaryServiceImpl;
import com.codegym.penzuproject.service.Impl.RoleServiceImpl;
import com.codegym.penzuproject.service.Impl.TagServiceImpl;
import com.codegym.penzuproject.service.Impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc

public class AppConfig implements WebMvcConfigurer {

    @Bean
    public IUserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public IRoleService roleService() {
        return new RoleServiceImpl();
    }

    @Bean
    public IDiaryService diaryService() {
        return new DiaryServiceImpl();
    }

    @Bean
    public ITagService tagService() {
        return new TagServiceImpl();
    }
}
