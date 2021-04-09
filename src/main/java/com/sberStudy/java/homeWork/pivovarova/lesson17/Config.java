package com.sberStudy.java.homeWork.pivovarova.lesson17;

import com.sberStudy.java.homeWork.pivovarova.lesson5.TerminalImpl;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.PinValidator;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Server;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Terminal;
import com.sberStudy.java.homeWork.pivovarova.lesson5.bank.PinValidatorImpl;
import com.sberStudy.java.homeWork.pivovarova.lesson5.bank.TerminalServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Server server() {
        return new TerminalServer();
    }

    @Bean
    public PinValidator pinValidator(Server server) {
        return new PinValidatorImpl(server);
    }

    @Bean
    public Terminal terminal(Server server, PinValidator pinValidator) {
        return new TerminalImpl(server, pinValidator);
    }
}
