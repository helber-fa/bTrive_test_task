package pages;

import com.codeborne.selenide.Selenide;

public class MobilePhonesPage extends ProductPage{
    private static final String URL =
            "https://rozetka.com.ua/ua/mobile-phones/c80003/";

    public MobilePhonesPage open() {
        Selenide.open(URL);
        return this;
    }
}
