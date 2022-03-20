import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.atm.Amount;
import ru.otus.atm.AmountService;
import ru.otus.atm.Atm;
import ru.otus.atm.MoneyService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmTest {

    Atm  getAtm() {
        return new Atm(
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
        Atm atm = getAtm();
        assertEquals(10000, atm.getBalance());
    }

    @Test
    @DisplayName("Успешный ввод денег")
    public void AtmDepositTest() {
        Amount depositAmmount = new Amount(1, 0, 0, 0, 0, 0);
        Atm atm = getAtm();
        atm.deposit(24900);
        assertEquals(34900, atm.getBalance());
    }

    @Test
    @DisplayName("Успешный вывод денег")
    public void AtmWithdrawTest() {
        Atm atm = getAtm();
        atm.deposit(3000);
        atm.withdraw(7800);
        assertEquals(5200, atm.getBalance());
    }
}
