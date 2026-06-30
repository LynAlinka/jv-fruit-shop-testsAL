package core.basesyntax;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import java.util.List;

public class ShopService {
    private final OperationStrategy strategy;

    public ShopService(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public void process(List<FruitTransaction> transactions) {
        ShopStorage storage = ShopStorage.getInstance();
        for (FruitTransaction transaction : transactions) {
            OperationHandler handler = strategy.get(transaction.getOperation());
            if (handler != null) {
                handler.handle(storage, transaction);
            }
        }
    }
}
