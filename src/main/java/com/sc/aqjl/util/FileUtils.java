package com.sc.aqjl.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static void uploadMyFile(MultipartFile file, String path,
			String myFileName) throws FileNotFoundException {
		File dir = new File(path);
		if (!dir.exists()) {// 创建目录
			dir.mkdirs();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(file.getInputStream());
			// 为以防万一，以后写文件的路径尽量写成正双斜杠的从源文件中取数据，写到目标文件中
			bos = new BufferedOutputStream(new FileOutputStream(path + "\\"
					+ myFileName));

			byte[] buff = new byte[8192];
			for (int len = -1; (len = bis.read(buff)) != -1;) {
				bos.write(buff, 0, len);
			}
			bos.flush();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		}

	}

	static int state = -1;

	public static InputStream getImageStream(String imgurl) {

		URL url;
		InputStream inputStream = null;
		try {
			url = new URL(imgurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			state = conn.getResponseCode();
			if (state == 404) {
				return null;
			}
			inputStream = conn.getInputStream(); // 通过输入流获得图片数据
		} catch (Exception e) {

		}

		return inputStream;
	}

	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static void zip(String urls_str, OutputStream respondOut)
			throws Exception {
		String[] urls = urls_str.split(",");
		InputStream[] inputFiles = new InputStream[urls.length];
		for (int i = 0; i < urls.length; ++i) {
			InputStream in = getImageStream(urls[i]);
			inputFiles[i] = in;
		}
		zip(inputFiles, respondOut);

	}

	@SuppressWarnings("unused")
	public static void zip(InputStream[] inputFiles, OutputStream respondOut)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(respondOut);
		BufferedOutputStream bo = new BufferedOutputStream(out);
		for (int i = 0; i < inputFiles.length; i++) {
			zip(out, inputFiles[i], "pic" + i + ".png", bo);
		}
		bo.flush();
		bo.close();
		out.close(); // 输出流关闭

	}

	public static void zip(ZipOutputStream out, InputStream in, String base,
			BufferedOutputStream bo) throws Exception { // 方法重载
		if (in == null)
			return;
		out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
		BufferedInputStream bi = new BufferedInputStream(in);

		int b;
		int sum = 0;
		byte[] buffer = new byte[1024];
		while ((b = bi.read(buffer)) != -1) {
			bo.write(buffer, 0, b);
			sum += b;
		}
		System.out.println(sum);
		// out.closeEntry();
		bo.flush();
		bi.close();
		in.close(); // 输入流关闭

	}
}
