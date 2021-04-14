package com.sberStudy.java.homeWork.pivovarova.lesson18.dao;

import com.sberStudy.java.homeWork.pivovarova.lesson18.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDaoImpl implements RecipeDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcOperations parameterJdbcOperations;
    private SimpleJdbcInsertOperations insertOperations;
    private RowMapper<Recipe> recipeRowMapper;

    public RecipeDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.insertOperations = new SimpleJdbcInsert(dataSource)
                .withTableName("Recipe")
                .usingColumns("name", "stages")
                .usingGeneratedKeyColumns("id");

        this.recipeRowMapper = (resultSet, i) -> {
            Recipe recipe = new Recipe();
            recipe.setId(resultSet.getInt("id"));
            recipe.setName(resultSet.getString("name"));
            recipe.setStages(resultSet.getString("stages"));
            return recipe;
        };
    }


    @Override
    public void createRecipe(Recipe recipe) {
        int returnKey = (int) insertOperations.executeAndReturnKey(new BeanPropertySqlParameterSource(recipe));
        createIngredients(recipe.getIngredients(), returnKey);
    }


    @Override
    public void showRecipe() {
        List<Recipe> recipe = jdbcTemplate.query("select * from Recipe", this.recipeRowMapper);
        for (Recipe r : recipe) {
            r.setIngredients(findIngredients(r.getId()));
        }
        for (Recipe r : recipe) {
            System.out.println(r);
        }
    }

    @Override
    public List<Recipe> findByNameOrId(String o) {
        List<Recipe> recipe;
        if (o.matches("-?\\d+(\\.\\d+)?")) {
            recipe = jdbcTemplate.query("select * from Recipe WHERE id = ?", new Object[]{o}, this.recipeRowMapper);
        } else {
            recipe = jdbcTemplate.query("select * from Recipe WHERE name regexp ?", new Object[]{o}, this.recipeRowMapper);
        }
        for (Recipe r : recipe) {
            r.setIngredients(findIngredients(r.getId()));
        }
        if (recipe.size() == 0) {
            System.out.println("Рецепт не был найден");
            return null;
        }
        return recipe;
    }

    @Override
    public void deleteByNameOrId(String o) {
        List<Recipe> list = findByNameOrId(o);
        if (list.size() == 0) {
            System.out.println("Рецепт не был найден");
        } else {
            System.out.println("По вашему запросу был(и) найден(ы) рецепт(ы):");
            for (Recipe r : list) {
                System.out.println(r);
            }
            System.out.println("Вы действительно хотите удалить выбранные элементы? " +
                    "Для удаления укажите id выбранного рецепта или введите ОК для удаления всех, для отметы введите CANCEL");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str.matches("-?\\d+(\\.\\d+)?")) {
                deleteByNameOrId(str);
                System.out.println("Рецепт удален.");
                return;
            }
            switch (str) {
                case "ОК": {
                    deleteByNameOrId(o);
                    System.out.println("Рецепт(ы) удален(ы)");
                    break;
                }
                case "CANCEL": {
                    System.out.println("Операция отменена.");
                    break;
                }
                default: {
                    System.out.println("Команда не распознанна.");
                    break;
                }
            }
        }
    }

    @Override
    public void delete(String o) {
        if (o.matches("-?\\d+(\\.\\d+)?")) {
            jdbcTemplate.update("delete from from Recipe WHERE id = ?", new Object[]{o});
        } else {
            jdbcTemplate.update("delete from Recipe WHERE name regexp ?", new Object[]{o});
        }
    }

    private Map<String, Integer> findIngredients(int id) {
        Map<String, Integer> mapIngredients = new HashMap<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet("select * from Ingredients WHERE id_recipe = ?", new Object[]{id});
        while (result.next()) {
            mapIngredients.put(result.getString("name"), result.getInt("quantity"));
        }
        return mapIngredients;
    }


    private void createIngredients(Map<String, Integer> ingredients, int id) {
        ingredients.forEach((k, v) -> {
            jdbcTemplate.update("insert into Ingredients (id_recipe, name, quantity) values (?, ?, ?)", preparedStatement -> {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, k);
                preparedStatement.setInt(3, v);
            });
        });
    }
}
