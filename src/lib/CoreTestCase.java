package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private final static String PLATFORM_IOS = "ios";
    private final static String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    String platform = System.getenv("PLATFORM");

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        if(platform.equals("android")) {
            driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
        } else if (platform.equals("ios")) {
            driver = new IOSDriver(new URL(APPIUM_URL), capabilities);
        }
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        driver.quit();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Nokia 7 plus");
            capabilities.setCapability("platformVersion", "10");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("app", "/Users/melnik/IdeaProjects/MobileAutoHomeTask05/apks/wiki.apk");
        } else if(platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone Xr");
            capabilities.setCapability("platformVersion", "13.3");
            capabilities.setCapability("app", "/Users/melnik/IdeaProjects/MobileAutoHomeTask05/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

}
