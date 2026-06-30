package core.basesyntax.service;

import core.basesyntax.db.ShopStorage;

public interface ReportGenerator {
    String getReport(ShopStorage storage);
}

