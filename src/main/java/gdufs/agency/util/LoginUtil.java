package gdufs.agency.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class LoginUtil {
	public static HttpClient httpClient = new HttpClient();

	public static byte[] getImageInputStream() {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

		GetMethod getMethod = new GetMethod("http://jxgl.gdufs.edu.cn/jsxsd/verifycode.servlet");
		getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");

		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}

			// 读取内容
			InputStream inputStream = getMethod.getResponseBodyAsStream();

			byte[] buff = new byte[1024]; // buff用于存放循环读取的临时数据
			int rc = 0;
			while ((rc = inputStream.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			byte[] in_b = swapStream.toByteArray();

//			System.out.println("OK!");
//			System.out.println(inputStream);
			return in_b;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	}

	public static boolean doPost(String url, Map<String, Object> paramMap) {
		// 获取输入流
		InputStream is = null;
		BufferedReader br = null;
//		String result = null;
		// 设置httpClient连接主机服务器超时时间：15000毫秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
		// 创建post请求方法实例对象
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		// 设置post请求超时时间
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

		NameValuePair[] nvp = null;
		// 判断参数map集合paramMap是否为空
		if (null != paramMap && paramMap.size() > 0) {// 不为空
			// 创建键值参数对象数组，大小为参数的个数
			nvp = new NameValuePair[paramMap.size()];
			// 循环遍历参数集合map
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			// 获取迭代器
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();

			int index = 0;
			while (iterator.hasNext()) {
				Entry<String, Object> mapEntry = iterator.next();
				// 从mapEntry中获取key和value创建键值对象存放到数组中
				try {
//					 System.out.println(mapEntry.getKey()+" "+mapEntry.getValue().toString());
					nvp[index] = new NameValuePair(mapEntry.getKey(),
							new String(mapEntry.getValue().toString().getBytes("UTF-8"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				index++;
			}
		}
		// 判断nvp数组是否为空
		if (null != nvp && nvp.length > 0) {
			// 将参数存放到requestBody对象中
			postMethod.setRequestBody(nvp);
		}
		// 执行POST方法
		try {

			// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
			httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

			int statusCode = httpClient.executeMethod(postMethod);

			// 判断是否成功
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method faild: " + postMethod.getStatusLine());
			}

//			System.out.println(statusCode);

//            System.out.println(postMethod.getRequestEntity());

            String charSet =  postMethod.getResponseCharSet();
//            System.out.println(charSet);

			// 获取远程返回的数据
			is = postMethod.getResponseBodyAsStream();
			// 封装输入流
			br = new BufferedReader(new InputStreamReader(is, charSet));

			StringBuffer sbf = new StringBuffer();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sbf.append(temp).append("\r\n");
			}

//			result = sbf.toString();
			if (statusCode == 302)
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 释放连接
			postMethod.releaseConnection();
		}
		return false;
	}

	public static boolean Verify(String username, String password) {
		boolean result = false;
		String url = "https://jxgl.gdufs.edu.cn/jsxsd/xk/LoginToXkLdap";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("USERNAME", username);
		map.put("PASSWORD", password);
		for (int i = 0; i < 20; i++) {
			try {
				String vcode = ImgUtils.getAllOcr(getImageInputStream());
//				System.out.println(vcode);
				map.put("RANDOMCODE", vcode);

				result = doPost(url, map);
				if (result)
					break;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
