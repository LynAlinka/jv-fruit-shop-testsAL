package core.basesyntax;

public class ReturnOperation implements OperationHandler {
    @Override
    public void handle(ShopStorage storage, FruitTransaction transaction) {
        int current = storage.getFruitQuantity(transaction.getFruit());
        storage.setFruitQuantity(transaction.getFruit(), current + transaction.getQuantity());
    }
}

