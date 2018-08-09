package com.bonc.businesscircle.util;


import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("deprecation")
public class HttpPostUtil {
	URL url;
	HttpURLConnection conn;
	String boundary = "--------httpPost";
	Map<String, String> textParams = new HashMap<String, String>();
	Map<String, byte[]> fileparams = new HashMap<String, byte[]>();
	DataOutputStream ds;

	public HttpPostUtil(String url) throws Exception {
		this.url = new URL(url);
	}
    //重新设置要请求的服务器地址，即上传文件的地址。
	public void setUrl(String url) throws Exception {
		this.url = new URL(url);
	}
    //增加一个普通字符串数据到form表单数据中
	public void addTextParameter(String name, String value) {
		textParams.put(name, value);
	}
    //增加一个文件到form表单数据中
	public void addFileParameter(String name, byte[] value) {
		fileparams.put(name, value);
	}
    // 清空所有已添加的form表单数据
	public void clearAllParameters() {
		textParams.clear();
		fileparams.clear();
	}
    // 发送数据到服务器，返回一个包含服务器的返回结果的字节数组
	public byte[] send() throws Exception {
		initConnection();
		try {
			conn.connect();
		} catch (SocketTimeoutException e) {
			// something
			System.out.println("超时！");
			throw new RuntimeException();
		}
		ds = new DataOutputStream(conn.getOutputStream());
		writeFileParams();
		writeStringParams();
		paramsEnd();
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		conn.disconnect();
		return out.toByteArray();
	}
    //文件上传的connection的一些必须设置
	private void initConnection() throws Exception {
		conn = (HttpURLConnection) this.url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);  
		conn.setUseCaches(false);
		conn.setConnectTimeout(100000); //连接超时为100秒
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
	}
    //普通字符串数据
	private void writeStringParams() throws Exception {
		Set<String> keySet = textParams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textParams.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\"" + name
					+ "\"\r\n");
			ds.writeBytes("\r\n");
			ds.writeBytes(encode(value) + "\r\n");
		}
	}
    //文件数据
	private void writeFileParams() throws Exception {
		Set<String> keySet = fileparams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			byte[] value = fileparams.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\"" + name
					+ "\"; filename=\"" + "tempFile" + "\"\r\n");
			ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
			ds.writeBytes("\r\n");
			ds.write(value);
			ds.writeBytes("\r\n");
		}
	}
    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
	private String getContentType(byte[] imgBytes) throws Exception {
		
//		return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型
		//ImageInputStream imagein = ImageIO.createImageInputStream(f);
		ImageInputStream imagein = new MemoryCacheImageInputStream(new ByteArrayInputStream(imgBytes));
//		if (imagein == null) {
//			return "application/octet-stream";
//		}
		Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
		if (!it.hasNext()) {
			imagein.close();
			return "application/octet-stream";
		}
		imagein.close();
		return "image/" + it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写

	}
    //把文件转换成字节数组
	private byte[] getBytes(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = in.read(b)) != -1) {
			out.write(b, 0, n);
		}
		in.close();
		return out.toByteArray();
	}
	//添加结尾数据
	private void paramsEnd() throws Exception {
		ds.writeBytes("--" + boundary + "--" + "\r\n");
		ds.writeBytes("\r\n");
	}
	// 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
    private String encode(String value) throws Exception{
    	return URLEncoder.encode(value, "UTF-8");
    }
   
	@SuppressWarnings({ "resource", "deprecation" })
	public static void main(String[] args) throws Exception {
//		File f = new File(
//				"/Users/wangxiaoyun/Downloads/iii.jpg");
//		String path = "http://test-yifenqiapi-app.yixin.com/yifenqiapp/common/uploadBigImg";
//		String fileParam = "";
////		String path = "http://localhost:8080/yifenqiapp/common/uploadBigImg";
//		MultipartEntity entity = new MultipartEntity();
//		 //This attaches the file to the POST:
//		HttpClient client = new DefaultHttpClient();
//		HttpPost httpPost = new HttpPost(path);
//
//		ContentBody fileBody = new FileBody(f); // file
//		entity.addPart("123", fileBody);
//		entity.addPart("333", fileBody);
//		entity.addPart("222", fileBody);
//		entity.addPart("token", new StringBody("login-1860036984068d3msqwhxuf3079"));
//		httpPost.setEntity(entity);
//		HttpResponse response = client.execute(httpPost);
//		System.out.println(response.toString());
		try {
			//回调IDC接口，返回匹配数
			String url = "http://192.168.0.188:8087/orgmgnt/task/receiveMatchResult";// 接口
			HttpPostUtil u = new HttpPostUtil(url);
			u.addTextParameter("taskId","697f953995554336bee48505b2c48bd7");
			u.addTextParameter("matchNum", "50");
			byte[] b = u.send();
			// 解析其中的data
			System.out.println(new String(b));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

