package ru.rencredit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebDriverSettings {

    public WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/WebDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}