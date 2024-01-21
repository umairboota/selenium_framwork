//package hooks;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//public class TestHooks {
//    public WebDriver driver;
//    public Properties config;
//    
//    public void setUpSuite() {
//        setUp();
//    }
//
//    public void setUp() {
//        config = loadConfig();
//        initializeWebDriver();
//    }
//
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public String getBaseUrl() {
//        if (config != null) {
//            return config.getProperty("baseUrl");
//        }
//        throw new IllegalStateException("Config not initialized");
//    }
//
//    public Properties loadConfig() {
//        Properties properties = new Properties();
//        try (FileInputStream input = new FileInputStream("src/test/java/config/config.properties")) {
//            properties.load(input);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return properties;
//    }
//
//    public void initializeWebDriver() {
//        String browser = getConfigProperty("browser");
//        // Set up WebDriver based on the 'browser' property from config
//        driver = initializeDriver(browser);
//
//        // Additional configurations for the WebDriver 
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    public WebDriver initializeDriver(String browser) {
//        WebDriver driver = null;
//
//        // Initialize driver based on the specified browser
//        if ("chrome".equalsIgnoreCase(browser)) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        } else if ("firefox".equalsIgnoreCase(browser)) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        } else if ("ie".equalsIgnoreCase(browser) || "internet explorer".equalsIgnoreCase(browser)) {
//            WebDriverManager.iedriver().setup();
//            driver = new InternetExplorerDriver();
//        } else if ("safari".equalsIgnoreCase(browser)) {
//            // Safari does not require WebDriverManager
//            driver = new SafariDriver();
//        } else {
//            throw new IllegalArgumentException("Unsupported browser: " + browser);
//        }
//
//        return driver;
//    }
//
//    private String getConfigProperty(String propertyName) {
//        if (config != null) {
//            return config.getProperty(propertyName);
//        }
//        throw new IllegalStateException("Config not initialized");
//    }
//}
