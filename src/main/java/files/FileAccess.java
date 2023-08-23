package files;

import jdk.jshell.spi.ExecutionControl;
import pojos.PriceEntry;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileAccess {
    private static FileAccess theInstance;

    private FileAccess () {}

    public List<PriceEntry> loadPriceEntries() {
        File entryDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "Awattar").toFile();
        List<File> entryFiles = new ArrayList<>(Arrays.stream(entryDir.listFiles()).toList());

        List<PriceEntry> priceEntries = new ArrayList<>();

        entryFiles.forEach(file -> {
            try {
                Files.lines(file.toPath(), StandardCharsets.ISO_8859_1).skip(1).forEach(line -> {
                    String[] lineParts = line.split(";");

                    LocalDateTime start = LocalDateTime.parse(lineParts[0], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
                    LocalDateTime end = LocalDateTime.parse(lineParts[1], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
                    double basePrice = Double.parseDouble(lineParts[2]);
                    String unit = lineParts[3];

                    priceEntries.add(new PriceEntry(start, end, basePrice * 1.2, unit));

                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return  priceEntries;

    }

    public static FileAccess getTheInstance() {
        if (theInstance == null) {
            theInstance = new FileAccess();
        }

        return theInstance;
    }
}
