package main.com.feed.services;

import main.com.feed.constants.Constants;
import main.com.feed.domain.Tweet;
import main.com.feed.error.InavlidFormatTextException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TweetService {

    public List<Tweet> getTweets(final String fileName) throws IOException {
        final FileService fileService = new FileService();
        final File file = fileService.getFileFromResources(fileName);
        assert file != null;
        return readTweetsFromFile(file);
    }

    private List<Tweet> readTweetsFromFile(final File file) throws IOException {
        final List<Tweet> tweets = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            final FileReader fileReader = new FileReader(file.getPath().trim().replaceAll("%20", " ")); // replace %20 with space to avoid File not found exception
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] userTweets = line.split(Constants.ARROW_SEPARATOR);
                if (isValidFormat(userTweets)) {
                    final Tweet tweet = new Tweet();
                    tweet.setUserName(userTweets[0].trim());
                    tweet.setTweet(userTweets[1].trim());
                    tweets.add(tweet);
                } else {
                    throw new InavlidFormatTextException("File not in correct format");
                }
            }
        } catch (IOException | InavlidFormatTextException e) {
            e.printStackTrace();
        } finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        return tweets;
    }

    private boolean isValidFormat(final String[] text) {
        return text.length == Constants.TWEET_SPLIT_COUNT;
    }
}
