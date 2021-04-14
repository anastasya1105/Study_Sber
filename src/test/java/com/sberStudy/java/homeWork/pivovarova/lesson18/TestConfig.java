package com.sberStudy.java.homeWork.pivovarova.lesson18;

import com.sberStudy.java.homeWork.pivovarova.lesson18.config.Config;
import com.sberStudy.java.homeWork.pivovarova.lesson18.dao.RecipeDao;
import com.sberStudy.java.homeWork.pivovarova.lesson18.model.Recipe;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class TestConfig {

    @BeforeAll
    public static void startServer() throws SQLException {
        Server.createTcpServer().start();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RecipeDao recipeDao;

    @Test
    public void addRecipe() {
        Map<String, Integer> ingredientsForPancakes = new HashMap<>();
        ingredientsForPancakes.put("молоко, мл", 500);
        ingredientsForPancakes.put("яйца, шт.", 3);
        ingredientsForPancakes.put("мука, г", 200);
        ingredientsForPancakes.put("масло сливочное, г", 30);
        ingredientsForPancakes.put("сахар, г", 30);
        ingredientsForPancakes.put("соль, г", 2);
        Recipe pancakes = Recipe.create("блины6", ingredientsForPancakes, "Перемешиваем до однородности миксером, наливаем тесто на раскалённую сковородку и одновременно вращаем её по кругу так");
        recipeDao.createRecipe(pancakes);
        Map<String, Integer> ingredientsForCutlet = new HashMap<>();
        ingredientsForCutlet.put("фарш (свинина + говядина), г", 500);
        ingredientsForCutlet.put("лук репчатый, шт.", 1);
        ingredientsForCutlet.put("яйцо, шт.", 1);
        ingredientsForCutlet.put("батон, г", 100);
        ingredientsForCutlet.put("молоко, мл", 150);
        ingredientsForCutlet.put("сливочное масло, г", 50);
        ingredientsForCutlet.put("растительное масло для жарки, г", 50);
        ingredientsForCutlet.put("соль, г", 2);
        ingredientsForCutlet.put("свежемолотый перец, г", 1);
        Recipe cutlet = Recipe.create("котлеты8", ingredientsForCutlet, "Лук очистить и мелко нарезать. Хорошо перемешать фарш руками. Из фарша сформировать котлеты и обжарить с двух сторон до румяности");
        recipeDao.createRecipe(cutlet);

        recipeDao.showRecipe(); // Для проверрки добавления
    }

    @Test
    public void findRecipe() {
        System.out.println(recipeDao.findByNameOrId("1"));
        System.out.println(recipeDao.findByNameOrId("отле"));
    }


    /*
    В классе RecipeDaoImpl есть еще метод deleteByNameOrId, который перед удалением выводит элементы, найденные по id или
    части имени блюда и перед удаление задает вопрос, но в тестах не получается вводить с консоли
     */
    @Test
    public void deleteRecipe() {
        recipeDao.delete("ины");
    }


}
