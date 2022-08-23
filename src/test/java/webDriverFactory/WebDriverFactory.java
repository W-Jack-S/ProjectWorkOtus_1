package webDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.Locale;

public class WebDriverFactory {
    public static String browser = System.getProperty("browser");
    public static String option = System.getProperty("options");
    public static WebDriver getDriver(){


        if (browser == null) {
            if (option != null) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments(option.toLowerCase(Locale.ROOT).trim());
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);
            }else {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }

        switch (browser.toLowerCase(Locale.ROOT).trim()){
            case "chrome":
                if (option != null) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(option.toLowerCase(Locale.ROOT).trim());
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver(options);
                }else {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                }
            case "firefox":
                if (option != null) {
                    FirefoxOptions options1 = new FirefoxOptions();
                    options1.addArguments(option.toLowerCase(Locale.ROOT).trim());
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver(options1);
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                }
            case "opera":
                if (option != null) {
                    OperaOptions options2 = new OperaOptions();
                    options2.addArguments(option.toLowerCase(Locale.ROOT).trim());
                    WebDriverManager.operadriver().setup();
                    return new OperaDriver(options2);
                } else {
                    WebDriverManager.operadriver().setup();
                    return new OperaDriver();
                }
            case "edge":
                if (option != null) {
                    EdgeOptions options3 = new EdgeOptions();
                    options3.addArguments(option.toLowerCase(Locale.ROOT).trim());
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver(options3);
                }else {
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();
                }

        }
        return new ChromeDriver();
    }

}
