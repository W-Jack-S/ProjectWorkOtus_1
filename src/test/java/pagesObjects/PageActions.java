package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class PageActions {
    public WebDriver driver;

    public PageActions(WebDriver driver){
        this.driver = driver;
    }

    public void moveCursor(By elementBy){
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(elementBy)).build().perform();
    }

    public void click(By elementBy){
        driver.findElement(elementBy).click();
    }

    public void textInput(By elementBy, String text) {
        String input = driver.findElement(elementBy).getAttribute("value");
        driver.findElement(elementBy).click();
        if (input != "") {
            driver.findElement(elementBy).clear();
        }
        //!!!!МЕТОД .clear() ОТРАБАТЫВАЕТ НЕ ВСЕГДА - ПРОБЛЕМЫ С ОЧИСТКОЙ ПОЛЕЙ С АВТОЗАПОЛНЕНИЕМ
        //НИЖЕ - АВАРИЙНЫЙ КОСТЫЛЬ: ЗАБИТЬ ПОЛЕ BackSpace'ом
        String inputAfter = driver.findElement(elementBy).getAttribute("value");
        while (inputAfter != "") {
            driver.findElement(elementBy).sendKeys("\b");
            inputAfter = driver.findElement(elementBy).getAttribute("value");
        }
        driver.findElement(elementBy).sendKeys(text);
    }

    public void scrollDownWithAJAX() throws InterruptedException {
        List<WebElement> events = driver.findElements(By.xpath("//a[@class='dod_new-event']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int eventsStart, eventsNext;
        do {
            eventsStart = events.size();
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(3000);
            events = driver.findElements(By.xpath("//a[@class='dod_new-event']"));
            eventsNext = events.size();
        } while (eventsNext > eventsStart);
        //while (check.findVisibleElement(xpath("//div[@class='dod_new-loader']/.."))); работал крайне нестабильно :( пришлось сделать более грубую прокрутку с Thread.sleep(3000);
    }
}
