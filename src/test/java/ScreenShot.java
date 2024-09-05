import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.util.Date;

public class ScreenShot {



    public static void screenshot(AndroidDriver driver, String pictureName) {

        try {
            String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

            String os_name = System.getProperty("os.name");
            String dir_name = System.getProperty("user.dir");
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            if (os_name.equals("Linux") || os_name.equals("Mac OS X")) {
                dir_name = dir_name + "/target/screenshot/";
                dirIsExists(dir_name);//判断目录是否存在，不存在则重新创建
            } else if (os_name.equals("Windows 7") || os_name.equals("Windows 10")) {
                dir_name = dir_name + "\\target\\screenshot\\";
                dirIsExists(dir_name);//判断目录是否存在，不存在则重新创建
            }
            FileUtils.copyFile(screenshot, new File(dir_name + pictureName + currentTime + ".jpg"));
        } catch (Exception e) {
        }
    }
    /**
     * 判断文件夹是否存在,不是则创建文件夹
     */
    private static void dirIsExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
    }
}


