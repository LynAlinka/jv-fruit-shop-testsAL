package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.strategy.ReturnOperation;
import core.basesyntax.strategy.SupplyOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopServiceTest {
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        ShopStorage.getInstance().setFruitQuantity("apple", 0);

        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        handlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        handlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        handlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        handlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());

        OperationStrategy strategy = new OperationStrategy(handlers);
        shopService = new ShopService(strategy);
    }

    @Test
    void process_validTransactions_ok() {
        FruitTransaction balanceApple = new FruitTransaction();
        balanceApple.setOperation(FruitTransaction.Operation.BALANCE);
        balanceApple.setFruit("apple");
        balanceApple.setQuantity(100);

        FruitTransaction purchaseApple = new FruitTransaction();
        purchaseApple.setOperation(FruitTransaction.Operation.PURCHASE);
        purchaseApple.setFruit("apple");
        purchaseApple.setQuantity(20);

        shopService.process(List.of(balanceApple, purchaseApple));

        int actualQuantity = ShopStorage.getInstance().getFruitQuantity("apple");
        assertEquals(80, actualQuantity, "100 balance - 20 purchase should equal 80");
    }
}