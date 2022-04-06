import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.atm.*;
import ru.otus.atm.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmImplTest {

    AtmImpl getAtm() {
        Amount amount = new Amount();

        amount.setBillAmount(new Nominal(100), 100);
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
        depositAmount.setBillAmount(new Nominal(5_000), 4);
        depositAmount.setBillAmount(new Nominal(2_000), 2);
        depositAmount.setBillAmount(new Nominal(500), 1);
        depositAmount.setBillAmount(new Nominal(200), 2);

        Atm atmImpl = getAtm();
        atmImpl.deposit(depositAmount);
        assertEquals(34900, atmImpl.getBalance());
    }

    @Test
    @DisplayName("Успешный вывод денег")
    public void AtmWithdrawTest() {
        Atm atmImpl = getAtm();
        Amount depositAmount = new Amount();
        depositAmount.setBillAmount(new Nominal(1_000), 3);
        atmImpl.deposit(depositAmount);
        atmImpl.withdraw(7800);
        assertEquals(5200, atmImpl.getBalance());
    }
}
