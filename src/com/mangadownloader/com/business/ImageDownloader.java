/**
 * 
 */
package com.mangadownloader.com.business;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.imageio.plugins.common.ImageUtil;

/**
 * @author aleco
 * 
 */
public class ImageDownloader {


	public static boolean downloadImage(String url, String imageSelector, String folderPath, String name) {
		// configureProxy();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element image = doc.select(imageSelector).first();
		String src = image.attr("src");
		int dotIndex = src.lastIndexOf(".");
		String ext = src.substring(dotIndex);
		try {
			URL imageUrl = new URL(src);
			InputStream in = imageUrl.openStream();
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					new File(folderPath + "/" + name + ext)));
			for (int b; (b = in.read()) != -1;) {
				out.write(b);
			}
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(folderPath + "/" + name + ext);
		return true;
	}

}
