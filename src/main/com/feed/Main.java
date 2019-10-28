package main.com.feed;

import main.com.feed.services.UserTweetService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        UserTweetService userTweetService = new UserTweetService();
        System.out.println(userTweetService.printTweets());
    }
}
