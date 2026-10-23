# Rozetka Product Scraper

A Java-based web scraper that collects product names and prices from the Rozetka mobile phones category using Selenide.

## Requirements

- Java 17
- Gradle (or the included Gradle Wrapper)
- Google Chrome

## Features

The scraper collects:
- Product names and prices from the first page of the mobile phones category.
- Products marked as **Top Sales**.

The collected data is exported to CSV files in the `output/` directory:

- `output/all-products.csv`
- `output/top-sales.csv`

## How to Run

### 1. Direct URL navigation — `Main`

The default entry point opens the mobile phones category directly:

`https://rozetka.com.ua/ua/mobile-phones/c80003/`

Run:

```bash
./gradlew run
```

### 2. Full category navigation — `MainFullPath`

This alternative entry point navigates through the Rozetka category menu before opening the mobile phones page.

Run:

```bash
./gradlew runFullPath
```

## Implementation Notes

### `Main`

Uses direct URL navigation to open the target category. This approach is simpler and avoids dependencies on the website's category menu structure.

### `MainFullPath`

Uses UI interactions to navigate through the category menu. This approach is more sensitive to changes in the website layout and you can face issues with captcha.

Both entry points reuse the product extraction and CSV export logic.

## Technologies

- Java 17
- Gradle
- Selenide
- Lombok

## Notes

The scraper depends on the current structure and availability of the Rozetka website. Changes to the page markup or access restrictions may require updates to the implementation.
