package utils;

import models.Product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvExporter {
    public void export(List<Product> products, Path path) {
        try {
            Files.createDirectories(path.getParent());

            List<String> lines = new java.util.ArrayList<>();
            lines.add("Name,Price,Top Sales");

            for (Product product : products) {
                lines.add(
                        csv(product.getName()) + "," +
                                csv(product.getPrice()) + "," +
                                product.isTopSales()
                );
            }

            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not export products to " + path, e
            );
        }
    }

    private String csv(String value) {
        String safeValue = value == null ? "" : value;
        return "\"" + safeValue.replace("\"", "\"\"") + "\"";
    }
}
