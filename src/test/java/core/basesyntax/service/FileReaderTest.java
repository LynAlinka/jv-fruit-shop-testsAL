package core.basesyntax.service;

import java.util.List;

import core.basesyntax.service.impl.FileReaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileReaderTest {
    private final FileReader fileReader = new FileReaderImpl();

    @Test
    void read_validFile_ok() {
        List<String> actual = fileReader.read("src/main/resources/input.csv");
        Assertions.assertFalse(actual.isEmpty(), "File should contain lines.");
        Assertions.assertEquals("type,fruit,quantity", actual.get(0), "Header line should match.");
    }

    @Test
    void read_nonExistentFile_notOk() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            fileReader.read("invalid/path/to/file.csv");
        }, "Should throw RuntimeException if file doesn't exist.");
    }
}
