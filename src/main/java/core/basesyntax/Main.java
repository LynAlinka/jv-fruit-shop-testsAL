package core.basesyntax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        handlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
        handlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
        handlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
        handlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());

        OperationStrategy strategy = new OperationStrategy(handlers);

        FileReader fileReader = new FileReaderImpl();
        List<String> inputData = fileReader.read("src/main/resources/input.csv");

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convert(inputData);

        ShopService shopService = new ShopService(strategy);
        shopService.process(transactions);

        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        String report = reportGenerator.getReport(ShopStorage.getInstance());

        FileWriter fileWriter = new FileWriterImpl();
        fileWriter.write(report, "src/main/resources/report.csv");
    }
}


