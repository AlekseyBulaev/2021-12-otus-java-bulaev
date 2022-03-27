import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.atm.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmImplTest {

    AtmImpl getAtm() {
        Amount amount = new Amount();
        amount.addBill(Bill.ONE_HUNDRED, 100);
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
        depositAmount.addBill(Bill.FIVE_THOUSAND, 4);
        depositAmount.addBill(Bill.TWO_THOUSAND, 2);
        depositAmount.addBill(Bill.FIVE_HUNDRED, 1);
        depositAmount.addBill(Bill.TWO_HUNDRED, 2);

        Atm atmImpl = getAtm();
        atmImpl.deposit(depositAmount);
        assertEquals(34900, atmImpl.getBalance());
    }

    @Test
    @DisplayName("Успешный вывод денег")
    public void AtmWithdrawTest() {
        Atm atmImpl = getAtm();
        Amount depositAmount = new Amount();
        depositAmount.addBill(Bill.ONE_THOUSAND, 3);
        atmImpl.deposit(depositAmount);
        atmImpl.withdraw(7800);
        assertEquals(5200, atmImpl.getBalance());
    }
}
