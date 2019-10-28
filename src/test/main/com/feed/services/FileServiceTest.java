package main.com.feed.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileServiceTest {


    @DisplayName("Should not return null when getting file given an existing file name")
    @Test
    void testGetFileFromResourceFolder() {
        FileService fileService = new FileService();
        Assertions.assertNotNull(fileService.getFileFromResources("user.txt"));
    }

    @DisplayName("Should throw exception when getting file given a non existing file name")
    @Test
    void testGetNonExistingFileFromResourceFolder() {
        FileService fileService = new FileService();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fileService.getFileFromResources("doesnt_exist");
        });
        Assertions.assertTrue(thrown.getMessage().contains("File not found"));
    }
}
