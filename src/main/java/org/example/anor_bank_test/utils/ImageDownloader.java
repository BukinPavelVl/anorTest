package org.example.anor_bank_test.utils;

import java.io.*;
import java.net.URL;

public class ImageDownloader {
	
	public static byte[] downloadImage(String urlString) throws IOException {
		URL url = new URL(urlString);
		try (InputStream in = url.openStream()) {
			return in.readAllBytes();
		}
	}
}

