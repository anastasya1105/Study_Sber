package com.sberStudy.java.homeWork.pivovarova.lesson18.model;

import java.util.Map;
import java.util.Objects;

public class Recipe {
    private int id;
    private String name;
    private Map<String, Integer> ingredientsAndQuantity;
    private String stages;

    public static Recipe create(String name, Map ingredientsAndQuantity, String stages) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setIngredients(ingredientsAndQuantity);
        recipe.setStages(stages);

        return recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredientsAndQuantity;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredientsAndQuantity = ingredients;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && Objects.equals(name, recipe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredientsAndQuantity=" + ingredientsAndQuantity +
                ", stages='" + stages + '\'' +
                '}';
    }
}
