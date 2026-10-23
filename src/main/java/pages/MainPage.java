package pages;

import com.codeborne.selenide.Selenide;

public class MainPage extends BasePage{
    private static final String URL = "https://rozetka.com.ua/ua/";

    public MainPage open() {
        Selenide.open(URL);
        return this;
    }
}
