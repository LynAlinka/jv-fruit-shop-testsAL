package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataConverterTest {
    private static DataConverter dataConverter;

    @BeforeAll
    static void beforeAll() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void convert_validLines_ok() {
        List<String> inputLines = List.of(
                "type,fruit,quantity",
                "b,banana,20",
                "s,apple,100"
        );

        List<FruitTransaction> actual = dataConverter.convert(inputLines);

        Assertions.assertEquals(2, actual.size(),
                "Should parse exactly 2 transactions (ignoring header)");

        // Перевіряємо першу транзакцію
        Assertions.assertEquals(FruitTransaction.Operation.BALANCE,
                actual.get(0).getOperation());
        Assertions.assertEquals("banana", actual.get(0).getFruit());
        Assertions.assertEquals(20, actual.get(0).getQuantity());

        // Перевіряємо другу транзакцію
        Assertions.assertEquals(FruitTransaction.Operation.SUPPLY,
                actual.get(1).getOperation());
        Assertions.assertEquals("apple", actual.get(1).getFruit());
        Assertions.assertEquals(100, actual.get(1).getQuantity());
    }

    @Test
    void convert_emptyList_ok() {
        List<FruitTransaction> actual = dataConverter.convert(new ArrayList<>());
        Assertions.assertTrue(actual.isEmpty(), "Result should be empty for empty input");
    }

    @Test
    void convert_invalidOperationCode_notOk() {
        List<String> invalidInput = List.of("x,banana,20"); // 'x' - невідомий код операції

        Assertions.assertThrows(RuntimeException.class, () -> {
            dataConverter.convert(invalidInput);
        }, "Should throw an exception for unknown operation code");
    }
}
