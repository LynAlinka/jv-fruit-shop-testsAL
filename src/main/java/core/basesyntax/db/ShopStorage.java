package core.basesyntax.db;

import java.util.HashMap;
import java.util.Map;

public class ShopStorage {
    private static final ShopStorage instance = new ShopStorage();
    private final Map<String, Integer> storage = new HashMap<>();

    private ShopStorage() {
    }

    public static ShopStorage getInstance() {
        return instance;
    }

    public void setFruitQuantity(String fruit, int quantity) {
        storage.put(fruit, quantity);
    }

    public int getFruitQuantity(String fruit) {
        return storage.getOrDefault(fruit, 0);
    }

    public Map<String, Integer> getAllFruits() {
        return new HashMap<>(storage);
    }
}
