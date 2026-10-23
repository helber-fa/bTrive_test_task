package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.Product;

import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;

public class ProductPage extends BasePage {
    private static final String productCard = "//rz-catalog-tile";
    private static final String productName = ".//a[contains(@class, 'text-base')]";
    private static final String productPrice = ".//div[contains(@class, 'price') and not (contains(@class, 'old-price'))]";
    private static final String productLabel = ".//rz-promo-label";

    private ElementsCollection productCards() {
        return $$x(productCard).shouldHave(sizeGreaterThan(0));
    }

    public List<Product> getAllProducts() {
        return productCards()
                .stream()
                .map(this::extractProduct)
                .toList();
    }

    public List<Product> getTopSalesProducts() {
        return getAllProducts()
                .stream()
                .filter(Product::isTopSales)
                .toList();
    }

    private Product extractProduct(SelenideElement card) {
        String name = card.$x(productName)
                .getText()
                .trim();

        String price = card.$x(productPrice).exists()
                ? card.$x(productPrice).getText().trim()
                : "";

        boolean topSales = card.$$x(productLabel)
                .texts()
                .stream()
                .map(text -> text.toUpperCase(Locale.ROOT))
                .anyMatch(text -> text.contains("ТОП ПРОДАЖІВ"));

        return new Product(name, price, topSales);
    }
}
