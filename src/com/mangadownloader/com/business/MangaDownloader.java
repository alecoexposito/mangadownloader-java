/**
 * 
 */
package com.mangadownloader.com.business;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author aleco
 *
 */
public class MangaDownloader {

	String staticUrl = "http://www.mangareader.net/";
		
	public MangaDownloader() {
		configureProxy();
	}
	
	public void downloadChapter(String folderPath, String serialName, String chapterNumber) {
		
		String[] arr = null;
		String url = staticUrl + serialName + "/" + chapterNumber;
		int counter = 0;
		
		do {
			counter++;
			String finalFolderPath = folderPath + "/" + chapterNumber;
			File chapterFolder = new File(finalFolderPath);
			if(!chapterFolder.exists())
				chapterFolder.mkdirs();
			ImageDownloader.downloadImage(url, "div#imgholder img", finalFolderPath, String.valueOf(counter));
			url = getNextLink(url);
			arr = url.split("/");
		} while (arr.length == 6);
		
	}
	
	private String getNextLink(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements elems = doc.select("span.next a");
		Element link = elems.first();
		return link.attr("abs:href");
	}
	
	private void configureProxy() {
		System.setProperty("http.proxyHost", "192.168.1.4");
		System.setProperty("http.proxyPort", "3128");
		final String authPassword = "alecoalejandro";
        final String authUser = "aexposito";

        Authenticator.setDefault(new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                      authUser, authPassword.toCharArray());
             }
          });
        
		System.setProperty("http.proxyUser", "aexposito");
		System.setProperty("http.proxyPassword", "alecoalejandro");
		
	}

	public void downloadInterval(String folderPath, String serialName, Integer firstChapter, Integer lastChapter) {
		if(firstChapter > lastChapter) {
			System.out.println("El capitulo inicial debe ser menor o igual que el final \n");
			return;
		}
		if(firstChapter == lastChapter) {
			downloadChapter(folderPath, serialName, firstChapter.toString());
			return;
		}
		int cantidad = lastChapter - firstChapter;
		int chapterNumber = firstChapter.intValue();
		for (int i = 0; i < cantidad + 1; i++) {
			downloadChapter(folderPath, serialName, String.valueOf(chapterNumber++));
		}
			
	}
}
