/**
 * 
 */
package com.mangadownloader.com.ui;

import java.util.Scanner;

import com.mangadownloader.com.business.MangaDownloader;

/**
 * @author aleco
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		if(true || args[0].equals("-i")) {
			System.out.println("Enter the manga folder: ");
			String folderPath = scanner.next();
			System.out.println("Enter the name of the manga: ");
			String serialName = scanner.next();
			System.out.println("Enter initial chapter number: ");
			String firstChapter = scanner.next();
			System.out.println("Enter final chapter number: ");
			String lastChapter = scanner.next();
			MangaDownloader downloader = new MangaDownloader();
			System.out.println("Downloading manga .... \n");
			downloader.downloadInterval(folderPath, serialName, Integer.valueOf(firstChapter), Integer.valueOf(lastChapter));
			System.out.println("Manga Downloaded \n");
		}			
		scanner.close();
	}

}
