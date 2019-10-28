package main.com.feed.services;

import main.com.feed.constants.Constants;
import main.com.feed.domain.Tweet;
import main.com.feed.domain.User;

import java.io.IOException;
import java.util.List;

public class UserTweetService {

    public String printTweets() throws IOException {
        StringBuilder output = new StringBuilder();
        final UserService userService = new UserService();
        final TweetService tweetService = new TweetService();
        final List<User> users = userService.sortUsers(userService.getUsers(Constants.USER_FILE_NAME));
        final List<Tweet> tweets = tweetService.getTweets(Constants.TWEET_FILE_NAME);
        if (!users.isEmpty()) {
            for (User user : users) {
                String userName = user.getName() + "\n";
                output.append(userName);
                tweets.forEach(tweet -> {
                    final boolean showTweet = user.getFollows().stream().anyMatch(follow -> follow.trim().equals(tweet.getUserName()) || tweet.getUserName().equals(user.getName()));
                    if (showTweet) {
                        String tweetText = "\t@" + tweet.getUserName() + ": " + tweet.getTweet() + "\n";
                        output.append(tweetText);
                    }
                });
            }
        }
        return output.toString();
    }
}
