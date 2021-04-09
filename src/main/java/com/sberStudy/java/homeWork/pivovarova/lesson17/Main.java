package com.sberStudy.java.homeWork.pivovarova.lesson17;


import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Server;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Terminal;
import com.sberStudy.java.homeWork.pivovarova.lesson5.bank.TerminalServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*Закоментированные методы создают базу данных, таблицу в ней и 4 клиентов банка,
и сохраняют их в базе на сервере. При заполнении базы на экран выводятся номер карты и пин-код,
чтобы в тестовом режими использовать их. Номер карты не проверяется на наличие в базе,
т.к. подразумевается, что если у человека есть карта, то ее номер правильный.
В созданной таблице есть клиент с картой: 11128014 и пин-кодом: 6626
*/
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Terminal terminal = context.getBean(Terminal.class);
//        Server server = context.getBean(TerminalServer.class);
//        server.createTable();
//        server.saveClient();
        terminal.work();
    }
}
