package core.basesyntax.strategy;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void handle(ShopStorage storage, FruitTransaction transaction) {
        int currentQuantity = storage.getFruitQuantity(transaction.getFruit());
        int newQuantity = currentQuantity - transaction.getQuantity();

        if (newQuantity < 0) {
            throw new RuntimeException("Not enough fruits "
                    + "in storage for purchase: " + transaction.getFruit());
        }

        storage.setFruitQuantity(transaction.getFruit(), newQuantity);
    }
}

