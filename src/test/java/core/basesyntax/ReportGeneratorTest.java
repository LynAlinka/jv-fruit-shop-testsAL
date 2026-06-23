package core.basesyntax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReportGeneratorTest {
    @Test
    void getReport_validStorage_ok() {
        ShopStorage storage = ShopStorage.getInstance();
        storage.setFruitQuantity("banana", 20);
        storage.setFruitQuantity("apple", 100);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String actualReport = reportGenerator.getReport(storage);

        String expectedReport = "fruit,quantity" + System.lineSeparator()
                + "banana,20" + System.lineSeparator()
                + "apple,100";

        Assertions.assertEquals(expectedReport.trim(), actualReport.trim(),
                "The generated report does not match the expected format.");
    }
}
