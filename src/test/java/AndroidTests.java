import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AndroidTests {
    public static URL url;
    public static DesiredCapabilities capabilities;
    public static AndroidDriver<AndroidElement> driver;

    public AndroidTests() {
    }

    @BeforeSuite
    public void setupAppium() throws MalformedURLException {
        String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL("http://127.0.0.1:4723/wd/hub");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("app", "/Users/hmineva/Downloads/app-debug-19042019.apk");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("automationName", "UIAutomatorView");
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("resetKeyboard", "true");
        capabilities.setCapability("useNewWDA", false);
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
        driver.resetApp();
    }

    @AfterSuite
    public void uninstallApp() throws InterruptedException {
        driver.removeApp("com.bzytan.appiumeveryday");
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.closeApp();
    }

    @Test(
            enabled = true
    )
    public void checkSendKeysWorks() throws InterruptedException {
        MobileElement line = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.EditText");
        line.clear();
        line.sendKeys(new CharSequence[]{"Hi!"});
        driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
        MobileElement openAlertButton = (MobileElement)driver.findElementByClassName("android.widget.Button");
        openAlertButton.click();
        MobileElement respMessage = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View");
        Assert.assertEquals(respMessage.getAttribute("text"), "Hi!");
        MobileElement closeButton = (MobileElement)driver.findElementByClassName("android.widget.Button");
        closeButton.click();
        AndroidElement firstValue = (AndroidElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[6]/android.view.View");
        Assert.assertEquals(firstValue.getAttribute("text"), "0");
        MobileElement textView = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[5]");
        Assert.assertEquals(textView.getAttribute("text"), "Button pushed this many times:");
        MobileElement rectangle = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]");
        Assert.assertEquals(rectangle.getAttribute("text"), "value, label, hint");
        MobileElement plusButton = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[3]");
        plusButton.click();
        Assert.assertEquals(firstValue.getAttribute("text"), "1");
        MobileElement goToOtherScreenButton = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[2]");
        goToOtherScreenButton.click();
        MobileElement getResponse = (MobileElement)driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]");
        Assert.assertEquals(getResponse.getAttribute("text"), "1");
    }
}
