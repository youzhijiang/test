package java0.nio01;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 导入httpclient相关jar包，或者引入httpclient相关依赖
 * @author youzhijiang
 *
 */
public class SendRequest {
	public static void main(String[] args) {
		try {
            HttpClient client = new DefaultHttpClient();
            //发送请求
            HttpGet request = new HttpGet("http://localhost:8801");
            HttpResponse response = client.execute(request);

            //**请求发送,处理返回信息
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //读取服务器返回过来的字符串数据
                String strResult = EntityUtils.toString(response.getEntity());
                System.out.println(strResult);//返回信息"hello,nio"
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}
