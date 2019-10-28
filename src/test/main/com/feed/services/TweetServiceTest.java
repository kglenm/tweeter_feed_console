package main.com.feed.services;


import main.com.feed.domain.Tweet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TweetServiceTest {

    private static List<Tweet> tweetList1 = new ArrayList<>();

    @BeforeAll
    static void setup() {
        Tweet tweet1 = new Tweet("Alan", "If you have a procedure with 10 parameters, you probably missed some.");
        Tweet tweet2 = new Tweet("Ward", "There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.");
        Tweet tweet3 = new Tweet("Alan", "Random numbers should not be generated with a method chosen at random.");
        tweetList1.add(tweet1);
        tweetList1.add(tweet2);
        tweetList1.add(tweet3);
    }

    @DisplayName("Should return list of tweets when file with tweets exists")
    @Test
    void testGetUserList() throws IOException {
        TweetService tweetService = new TweetService();
        Assertions.assertEquals(tweetService.getTweets("tweet.txt"), tweetList1);
    }

}
