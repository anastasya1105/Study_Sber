package com.sberStudy.java.homeWork.pivovarova.lesson5;


import com.sberStudy.java.homeWork.pivovarova.lesson5.api.PinValidator;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Server;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Terminal;
import com.sberStudy.java.homeWork.pivovarova.lesson5.bank.PinValidatorImpl;
import com.sberStudy.java.homeWork.pivovarova.lesson5.bank.TerminalServer;

/*Закоментированные методы создают базу данных, таблицу в ней и 4 клиентов банка,
и сохраняют их в базе на сервере. При заполнении базы на экран выводятся номер карты и пин-код,
чтобы в тестовом режими использовать их. Номер карты не проверяется на наличие в базе,
т.к. подразумевается, что если у человека есть карта, то ее номер правильный.
В созданной таблице есть клиент с картой: 11128014 и пин-кодом: 6626
*/
public class Main {
    public static void main(String[] args) {
        Server server = new TerminalServer();
//        server.createTable();
//        server.saveClient();
        PinValidator pinValidator = new PinValidatorImpl(server);
        Terminal terminal = new TerminalImpl(server, pinValidator);
        terminal.work();

    }
}
