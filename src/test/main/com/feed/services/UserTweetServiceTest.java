package main.com.feed.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class UserTweetServiceTest {

    @DisplayName("Should return true if a string is returned")
    @Test
    void testFindingIfUserExists() throws IOException {
        UserTweetService userTweetService = new UserTweetService();
        String output = userTweetService.printTweets();
        Assertions.assertNotNull(output);
    }

}
