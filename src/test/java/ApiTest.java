import org.apache.http.HttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApiTest {

    @Test
    public void getApiTest() throws IOException {
       RestClientUtils restClient=new RestClientUtils();

        HttpResponse httpResponse =  restClient.get("https://pda.weather.gov.hk/locspc/data/fnd_uc.xml");
        int responseStatusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertTrue(200==responseStatusCode);
        System.out.println("接口请求响应成功");

        System.out.println("response status code ==> " + responseStatusCode);

        String responseString = EntityUtils.toString(httpResponse.getEntity(), "utf8");

        System.out.println("response json from api ==> " + responseString);
        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray forecastDetailArray = jsonObject.getJSONArray("forecast_detail");
        // 获取当前日期
        Date currentDate = new Date();
        // 创建一个Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 将当前日期设置到Calendar实例中
        calendar.setTime(currentDate);
        // 将日期加上两天
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        // 获取计算结果
        Date resultDate = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String forecast_date= sdf.format(resultDate);
        for (int i = 0; i < forecastDetailArray.length(); i++) {
            JSONObject forecastObject = forecastDetailArray.getJSONObject(i);
            if (forecastObject.getString("forecast_date").equals(forecast_date)) {
                int min_rh=forecastObject.getInt("min_rh");
                int max_rh=forecastObject.getInt("max_rh");
                System.out.println("日期为"+forecast_date+"相对湿度为"+min_rh+"%-"+max_rh+"%");

            }
        }
    }

}
