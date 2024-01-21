package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtils {
	public static WebDriver driver;
    public static Properties config;
    
    public static void clickElement(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }

    public static void setInput(WebDriver driver, By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    
    public void setUpSuite() {
        setUp();
    }

    public void setUp() {
        config = loadConfig();
        initializeWebDriver();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        if (config != null) {
            return config.getProperty("baseUrl");
        }
        throw new IllegalStateException("Config not initialized");
    }

    public Properties loadConfig() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/java/config/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @SuppressWarnings("deprecation")
	public void initializeWebDriver() {
        String browser = getConfigProperty("browser");
        // Set up WebDriver based on the 'browser' property from config
        driver = initializeDriver(browser);

        // Additional configurations for the WebDriver 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebDriver initializeDriver(String browser) {
        WebDriver driver = null;

        // Initialize driver based on the specified browser
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browser) || "internet explorer".equalsIgnoreCase(browser)) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if ("safari".equalsIgnoreCase(browser)) {
            // Safari does not require WebDriverManager
            driver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
    }
    
//    reading username from config file
    public String getUsername() {
        return getConfigProperty("username");
    }

//    reading username from config file
    public String getPassword() {
        return getConfigProperty("password");
    }

    
    private String getConfigProperty(String name) {
        if (config != null) {
            return config.getProperty(name);
        }
        throw new IllegalStateException("Config not initialized");
    }}
