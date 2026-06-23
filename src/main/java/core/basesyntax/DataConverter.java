package core.basesyntax;

import java.util.List;

public interface DataConverter {
    List<FruitTransaction> convert(List<String> value);
}

