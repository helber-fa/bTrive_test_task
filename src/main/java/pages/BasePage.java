package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {
    private final SelenideElement catalogButton = $x("//button[@data-testid='fat_menu_btn']");
    private final String mainCategoryXpathPattern = "//ul[@class = 'list']//a[contains(@class, 'category-link') and normalize-space(text())='%s']";
    private final String additionalCategoryXpathPattern = "//ul[contains(@class, 'list-content')]//a[normalize-space(text())='%s']";

    public ProductPage openCategory(String mainCategory) {
        openCatalog();
        SelenideElement mainCategoryElement = $x(String.format(mainCategoryXpathPattern,mainCategory));
        mainCategoryElement.should(visible).click();
        return new ProductPage();
    }


    public ProductPage openCategory(String mainCategory, String additionalCategory) {
        openCatalog();

        SelenideElement mainCategoryElement = $x(String.format(mainCategoryXpathPattern,mainCategory));
        SelenideElement additionalCategoryElement = $x(String.format(additionalCategoryXpathPattern,additionalCategory));
        mainCategoryElement
                .should(visible)
                .hover();

        additionalCategoryElement
                .should(visible)
                .click();

        return new ProductPage();
    }

    private void openCatalog() {
        catalogButton.shouldBe(visible).click();
    }
}
