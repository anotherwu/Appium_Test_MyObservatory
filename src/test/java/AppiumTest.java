
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppiumTest {
    private AndroidDriver driver;



    @BeforeTest
    public void setUp() throws Exception {
        System.out.println("@BeforeTest");
        // 设置Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.1.2");
        caps.setCapability("noReset", true);
        caps.setCapability("fullReset", false);
        caps.setCapability("deviceName", "127.0.0.1:62001");
        caps.setCapability("appPackage", "hko.MyObservatory_v1_0");
        caps.setCapability("appActivity", ".AgreementPage");

        // 初始化driver

        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        this.driver = driver;
        System.out.println("@BeforTest Over");
    }

    @Test
    public void getNineDayWeatherForecast() throws InterruptedException {
        System.out.println(" @Test");
        Thread.sleep(20000);
        ScreenShot.screenshot(driver, "首页");

        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"向上瀏覽\"]\n").click();


        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"hko.MyObservatory_v1_0:id/title\" and @text=\"預報及警告服務\"]").click();


        Thread.sleep(2000);
        ScreenShot.screenshot(driver, "九天预报按钮页面");

        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"hko.MyObservatory_v1_0:id/title\" and @text=\"九天預報\"]").click();

        Thread.sleep(2000);

        ScreenShot.screenshot(driver, "九天预报详情页面");
        System.out.println("@Test Over");

    }


/*
    @AfterMethod
    private void logout(ITestResult iTestResult){ //ITestResult 中存有test执行结果
        int status = iTestResult.getStatus();
        String method = iTestResult.getMethod().getMethodName();
        //执行失败，屏幕截图

            ScreenShot.screenshot(driver, method);

    }
*/


    @AfterTest
    public void tearDown() {
        System.out.println("@AfterTest");



        if (driver != null) {
            driver.quit();
        }
        System.out.println("@AfterTest Over");
    }
}
