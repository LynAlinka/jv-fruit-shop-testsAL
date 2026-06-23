package core.basesyntax;

import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    @Override
    public String getReport(ShopStorage storage) {
        StringBuilder sb = new StringBuilder("fruit,quantity")
                .append(System.lineSeparator());
        for (Map.Entry<String, Integer> entry : storage.getAllFruits().entrySet()) {
            sb.append(entry.getKey()).append(",")
                    .append(entry.getValue()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}

