package com.sberStudy.java.homeWork.pivovarova.lesson18.dao;

import com.sberStudy.java.homeWork.pivovarova.lesson18.model.Recipe;

import java.util.List;

public interface RecipeDao {
    void createRecipe(Recipe recipe);
    List<Recipe> findByNameOrId(String o);
    void deleteByNameOrId(String o);
    void showRecipe();
    void delete(String o);
}
