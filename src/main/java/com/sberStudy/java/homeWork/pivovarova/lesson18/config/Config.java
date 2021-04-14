package com.sberStudy.java.homeWork.pivovarova.lesson18.config;

import com.sberStudy.java.homeWork.pivovarova.lesson18.dao.RecipeDao;
import com.sberStudy.java.homeWork.pivovarova.lesson18.dao.RecipeDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import(DataConfiguration.class)
public class Config {

    @Bean
    public RecipeDao recipeDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return new RecipeDaoImpl(dataSource, jdbcTemplate);
    }
}
