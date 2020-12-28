package study.lessonExceptions.Terminal;

import study.lessonExceptions.ClientAccount;
import study.lessonExceptions.MyExceptions.AccountBalanceException;

import java.util.HashMap;
import java.util.Map;

public class TerminalServer implements Terminal {
    private static TerminalServer instance;
    Map<Integer, ClientAccount> accountMap;

    /**
     * При старте сервера, выполняется инициализация клиентской базы.
     */
    private TerminalServer() {

        accountMap = new HashMap<>();

        ClientAccount account1 = new ClientAccount();
        account1.plusBalance(0);

        ClientAccount account2 = new ClientAccount();
        account2.plusBalance(100);

        ClientAccount account3 = new ClientAccount();
        account3.plusBalance(500);

        accountMap.put(111, account1);
        accountMap.put(222, account2);
        accountMap.put(333, account3);
    }

    public static TerminalServer getInstance() {
        if (instance == null) {
            instance = new TerminalServer();
        }
        return instance;
    }

    @Override
    public int checkAccount(ClientAccount clientAccount) {
        return clientAccount.getBalance();
    }

    @Override
    public void plusBalance(ClientAccount clientAccount, int sum) {
        clientAccount.plusBalance(sum);
    }

    @Override
    public void minusBalance(ClientAccount clientAccount, int sum) throws AccountBalanceException {
        clientAccount.minusBalance(sum);
    }

    public ClientAccount getClientByPIN(Integer pin) {
        return accountMap.get(pin);
    }
}
