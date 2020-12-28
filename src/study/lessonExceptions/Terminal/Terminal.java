package study.lessonExceptions.Terminal;

import study.lessonExceptions.ClientAccount;
import study.lessonExceptions.MyExceptions.AccountBalanceException;

public interface Terminal {
    int checkAccount(ClientAccount clientAccount);

    public void plusBalance(ClientAccount clientAccount, int sum);

    public void minusBalance(ClientAccount clientAccount, int sum) throws AccountBalanceException;
}
