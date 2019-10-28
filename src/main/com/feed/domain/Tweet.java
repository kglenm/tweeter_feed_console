package main.com.feed.domain;

import java.util.Objects;

public class Tweet {

    private String userName;

    private String tweet;

    public Tweet() {
    }

    public Tweet(final String userName, final String tweet) {
        this.userName = userName;
        this.tweet = tweet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(final String tweet) {
        this.tweet = tweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet1 = (Tweet) o;
        return Objects.equals(userName, tweet1.userName) &&
                Objects.equals(tweet, tweet1.tweet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, tweet);
    }
}
