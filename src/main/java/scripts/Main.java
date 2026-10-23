package scripts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import models.Product;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MobilePhonesPage;
import utils.CsvExporter;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "prefs",
                Map.of("profile.default_content_setting_values.notifications", 2)
        );
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10_000;
        try {
            MobilePhonesPage mobilePhonesPage = new MobilePhonesPage().open();
            List<Product> allProducts = mobilePhonesPage.getAllProducts();
            List<Product> topSalesProducts = mobilePhonesPage.getTopSalesProducts();
            CsvExporter exporter = new CsvExporter();

            exporter.export(
                    allProducts,
                    Path.of("output", "all-products.csv")
            );

            exporter.export(
                    topSalesProducts,
                    Path.of("output", "top-sales.csv")
            );

            System.out.println("Products collected: " + allProducts.size());
            System.out.println("Top Sales products: " + topSalesProducts.size());
            System.out.println("Results exported to output/");
        } finally {
            if (WebDriverRunner.hasWebDriverStarted()) {
                WebDriverRunner.closeWebDriver();
            }
        }
    }
}
