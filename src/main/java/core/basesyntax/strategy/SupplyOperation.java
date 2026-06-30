package core.basesyntax.strategy;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.model.FruitTransaction;

public class SupplyOperation implements OperationHandler {
    @Override
    public void handle(ShopStorage storage, FruitTransaction transaction) {
        int current = storage.getFruitQuantity(transaction.getFruit());
        storage.setFruitQuantity(transaction.getFruit(), current + transaction.getQuantity());
    }
}
