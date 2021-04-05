package com.sberStudy.java.homeWork.pivovarova.lesson5;

public class Main {
    public static void main(String[] args) {
        TerminalServer server = new TerminalServer();
        TerminalImpl terminal = new TerminalImpl(server, new PinValidator(server));
        terminal.work();
    }
}
