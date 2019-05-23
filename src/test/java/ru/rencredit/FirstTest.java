package ru.rencredit;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends WebDriverSettings {


    @Test
    public void createDeposit() {
        // Открыть сайт rencredit.ru
        openMainPage();
        // Перейти на страницу "Вклад"
        toPageDeposit();
        // Выбрать чекбокс "В отделении банка"
        chooseCheckBoxInBank();
        // Ввести сумму вклада
        enterDepositSum();
        // Передвинуть ползунок "На срок"
        moveSlider();
        // Выгрузить Печатную Форму "Общие условия по вкладам"
        downloadPrintForm();
    }

    @Test
    public void getCard() {
        // Открыть сайт rencredit.ru
        openMainPage();
        // Перейти на страницу "Карта"
        openCreditCardPage();
        // Ввести значения в поля "Фамилия", "Имя", "Мобильный телефон", "e-mail"
        fillTheForm();
        // Выбрать чекбокс "Нет отчества"
        chooseCheckBoxNoMiddleName();
        // Выбрать значение из выпадающего списка "Где вы желаете получить карту"
        chooseThePlaceOfCardReceiving();
    }


    @Step("Выбран чекбокс \"Нет отчества\"")
    private void chooseCheckBoxNoMiddleName() {
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Нет отчества'])[1]/div[1]")).click();
        driver.findElement(By.name("ClientSecondName")).getAttribute("disabled");
    }

    @Step("Заполнены поля \"Фамилия\", \"Имя\", \"Мобильный телефон\", \"e-mail\"")
    private void fillTheForm() {
        driver.findElement(By.xpath("//input[@id='t1']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//input[@id='t2']")).sendKeys("Петр");
        driver.findElement(By.xpath("//input[@id='t4']")).click();
        driver.findElement(By.xpath("//input[@id='t4']")).sendKeys("9991122331");
        driver.findElement(By.xpath("//input[@id='t38']")).sendKeys("email@yandex.ru");
    }

    @Step("Переход на страницу заявки на кредитную карту")
    private void openCreditCardPage() {
        driver.findElement(By.xpath("//a[@class='footer__nav-link underline-link'][contains(text(),'Карты')]")).click();
        driver.findElement(By.xpath("//a[@class='btn btn_red cards-list__item-btn']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Заберите карту в ближайшем отделении')]")).isDisplayed();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Заберите карту в ближайшем отделении')]")).isDisplayed(), "Сообщение \"Заберите карту в ближайшем отделении\" отображается");
    }

    @Step("Выгружена печатная форма")
    private void downloadPrintForm() {
        driver.findElement(By.xpath(".//a[text()='Общие условия по вкладам']")).click();
    }

    @Step("Выбрано значение из выпадающего списка \"Где вы желаете получить карту\"")
    private void chooseThePlaceOfCardReceiving() {
        driver.findElement(By.xpath("//div[contains(text(),'Где Вы желаете получить карту?')]")).click();
        driver.findElement(By.xpath("//*[@id=\"s2-styler\"]/div[2]/ul/li[3]")).click();
    }

    @Step("Введена сумма депозита")
    private void enterDepositSum() {
        driver.findElement(By.xpath("//input[@name='amount']")).sendKeys("300000");
    }

    @Step("Выбран чек-бокс \"В отделении банка\"")
    private void chooseCheckBoxInBank() {
        driver.findElement(By.xpath("//div[@class='calculator__check-row-field check-deposit']")).click();
    }

    @Step("Ползунок передвинут")
    private void moveSlider() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement a = driver.findElement(By.xpath("//div[@class='calculator__slide-section']//div[3]//div[1]//span[1]"));
        js.executeScript("arguments[0].setAttribute('style', 'left: 30%;')", a);
    }

    @Step("Переход на страницу \"Вклады\"")
    private void toPageDeposit() {
        driver.findElement(By.xpath("//a[@class='footer__nav-link underline-link'][contains(text(),'Вклады')]")).click();
        driver.findElement(By.xpath("//a[@class='btn btn_red btn_large calculator__btn']")).isDisplayed();
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='btn btn_red btn_large calculator__btn']")).isDisplayed(), "Кнопка \"Заполнить заявку на вклад\" отображается");
    }

    @Step("Открытие страницы")
    private void openMainPage() {
        driver.get("https://rencredit.ru");
        driver.findElement(By.xpath(".//div[@class='header__content']")).isDisplayed();
        Assert.assertTrue(driver.findElement(By.xpath(".//div[@class='header__content']")).isDisplayed(), "Страница отображается");
    }

}