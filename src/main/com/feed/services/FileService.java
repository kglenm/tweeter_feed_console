package main.com.feed.services;

import java.io.File;
import java.net.URL;

public class FileService {

    public File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException("File not found");
        }
        return new File(url.getFile());
    }
}
