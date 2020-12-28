package study.lessonExceptions.Terminal;

import study.lessonExceptions.ClientAccount;
import study.lessonExceptions.MyExceptions.AccountBalanceException;
import study.lessonExceptions.MyExceptions.AccountExceptions;
import study.lessonExceptions.MyExceptions.TerminalAmountException;
import study.lessonExceptions.Utils.Messages;
import study.lessonExceptions.Utils.PinValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalImpl implements Terminal {
    private final TerminalServer server = TerminalServer.getInstance();
    private final PinValidator pinValidator = new PinValidator(server);

    /**
     * Реализация бизнес-логики работы терминала
     */
    public void startTerminal() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {

                Messages.printConsoleMessage("Для выполнения операции, введите ПИН-код.");

                String s = reader.readLine().trim();
                ClientAccount clientAccount = null;

                if (validateInt(s)) {
                    try {
                        clientAccount = pinValidator.validatePIN(Integer.parseInt(s));
                    } catch (AccountExceptions e) {
                        Messages.consoleExceptionPrinter(e);
                        continue;
                    }

                    Messages.printConsoleMessage("Добро пожаловать! \nДля пополнения счета укажите + \n" +
                            "Для снятия наличных, укажите -\nДля запроса баланса, укажите 0\n" +
                            "Для выхода из терминала, нажмите q.");

                    String clientAction = reader.readLine().trim();

                    switch (clientAction) {
                        case "+": {
                            int intSumPlus = 0;
                            Messages.printConsoleMessage("Введите сумму для пополнения счета:");
                            String stringSum = reader.readLine();

                            if (validateInt(stringSum)) {
                                intSumPlus = Integer.parseInt(stringSum);
                                clientAccount.plusBalance(intSumPlus);
                                Messages.printConsoleMessage("Счет по полнен на " + intSumPlus + " едениц.");
                            } else {
                                Messages.printConsoleMessage("Сумма не распознана. Попробуйте выполнить операцию заново");
                            }
                            break;
                        }
                        case "-": {
                            int intSumMinus = 0;
                            Messages.printConsoleMessage("Введите сумму для снятия:");
                            String stringSum = reader.readLine();

                            if (validateInt(stringSum)) {
                                intSumMinus = Integer.parseInt(stringSum);

                                try {
                                    clientAccount.minusBalance(intSumMinus);
                                    Messages.printConsoleMessage("Списано " + intSumMinus + " едениц.");
                                } catch (AccountBalanceException e) {
                                    Messages.consoleExceptionPrinter(e);
                                }

                            } else {
                                Messages.printConsoleMessage("Сумма не распознана. Попробуйте выполнить операцию заново");
                            }
                            break;
                        }
                        case "0": {
                            Messages.printConsoleMessage("Ваш баланс: " + clientAccount.getBalance() + " едениц.");
                            break;
                        }
                        default:
                            Messages.printConsoleMessage("Символ не распознан.");
                            break;
                    }

                } else {
                    Messages.printConsoleMessage("ПИН-код должен содержать только целые положительные числа. \n" +
                            "Введите код повторно.");
                }
            }
        } catch (IOException e) {
            Messages.printDefaultConsoleErr();
        }
    }

    private static boolean validateInt(String s) {
        return s.matches("[0-9]+");
    }

    @Override
    public int checkAccount(ClientAccount clientAccount) {
        return server.checkAccount(clientAccount);
    }

    @Override
    public void plusBalance(ClientAccount clientAccount, int sum) {
        try {
            checkAmount(sum);
        } catch (TerminalAmountException e) {
            Messages.consoleExceptionPrinter(e);
        }
        server.plusBalance(clientAccount, sum);
    }

    @Override
    public void minusBalance(ClientAccount clientAccount, int sum) {
        try {
            checkAmount(sum);
            server.minusBalance(clientAccount, sum);
        } catch (AccountBalanceException | TerminalAmountException e) {
            Messages.consoleExceptionPrinter(e);
        }
    }

    private void checkAmount(int sum) throws TerminalAmountException {
        if ((sum % 100) > 0) throw new TerminalAmountException("Для выполнения операции, введите сумму, кратную 100");
    }
}
