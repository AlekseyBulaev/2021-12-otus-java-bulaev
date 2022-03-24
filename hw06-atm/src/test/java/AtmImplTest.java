import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.atm.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmImplTest {

    AtmImpl getAtm() {
        return new AtmImpl(
                new Amount(
                        0,
                        0,
                        0,
                        0,
                        0,
                        100)
                , new MoneyService()
                , new AmountService());
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
        Amount depositAmount = new Amount(4, 2, 0, 1, 2, 0);
        Atm atmImpl = getAtm();
        atmImpl.deposit(depositAmount);
        assertEquals(34900, atmImpl.getBalance());
    }

    @Test
    @DisplayName("Успешный вывод денег")
    public void AtmWithdrawTest() {
        Atm atmImpl = getAtm();
        Amount depositAmmount = new Amount(0, 0, 3, 0, 0, 0);
        atmImpl.deposit(depositAmmount);
        atmImpl.withdraw(7800);
        assertEquals(5200, atmImpl.getBalance());
    }
}
