package com.codegym.penzuproject.config;

<<<<<<< HEAD
import com.codegym.penzuproject.service.ITagService;
import com.codegym.penzuproject.service.impl.TagServiceImpl;
=======
import com.codegym.penzuproject.service.IRoleService;
import com.codegym.penzuproject.service.IUserService;
import com.codegym.penzuproject.service.Impl.RoleServiceImpl;
import com.codegym.penzuproject.service.Impl.UserServiceImpl;
>>>>>>> origin/hoang
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc

public class AppConfig implements WebMvcConfigurer {

    @Bean
<<<<<<< HEAD
    public ITagService tagService(){
        return new TagServiceImpl();
=======
    public IUserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public IRoleService roleService() {
        return new RoleServiceImpl();
>>>>>>> origin/hoang
    }
}
