import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.atm.*;
import ru.otus.atm.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmImplTest {

    AtmImpl getAtm() {
        Amount amount = new Amount();

        amount.setBillAmount(new OneHundred(), 100);
        var atm = new AtmImpl(
                amount
                , new MoneyService()
                , new AmountService());

        return atm;
    }

    @Test
    @DisplayName("Успешный вывод баланса")
    public void AtmGetBalanceTest() {
        AtmImpl atmImpl = getAtm();
        assertEquals(10000, atmImpl.getBalance());
    }

    @Test
    @DisplayName("Успешный ввод денег")
    public void AtmDepositTest() {
        Amount depositAmount = new Amount();
        depositAmount.setBillAmount(new FiveThousand(), 4);
        depositAmount.setBillAmount(new TwoThousand(), 2);
        depositAmount.setBillAmount(new FiveHundred(), 1);
        depositAmount.setBillAmount(new TwoHundred(), 2);

        Atm atmImpl = getAtm();
        atmImpl.deposit(depositAmount);
        assertEquals(34900, atmImpl.getBalance());
    }

    @Test
    @DisplayName("Успешный вывод денег")
    public void AtmWithdrawTest() {
        Atm atmImpl = getAtm();
        Amount depositAmount = new Amount();
        depositAmount.setBillAmount(new OneThousand(), 3);
        atmImpl.deposit(depositAmount);
        atmImpl.withdraw(7800);
        assertEquals(5200, atmImpl.getBalance());
    }
}
