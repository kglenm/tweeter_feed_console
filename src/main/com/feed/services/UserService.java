package main.com.feed.services;

import main.com.feed.constants.Constants;
import main.com.feed.domain.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserService {

    public Set<User> getUsers(final String fileName) throws IOException {
        final FileService fileService = new FileService();
        final File file = fileService.getFileFromResources(fileName);
        assert file != null;
        return readUsersFromFile(file);
    }

    private Set<User> readUsersFromFile(final File file) throws IOException {
        final Set<User> users = new HashSet<>();
        BufferedReader bufferedReader = null;
        try {
            final FileReader fileReader = new FileReader(file.getPath().trim().replaceAll("%20", " ")); // replace %20 with space to avoid File not found exception
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userNames = line.split(Constants.FOLLOW_REGEX);
                if (isValidFormat(userNames)) {
                    String trimmedUsername = userNames[0].trim();
                    User user = new User();

                    if (!existsInSet(users, trimmedUsername)) {
                        user.setName(trimmedUsername);
                        users.add(user);
                    } else {
                        user = users.stream().filter(usr -> usr.getName().equals(trimmedUsername)).findFirst().orElse(new User());
                    }

                    String[] followees = userNames[1].split(",");
                    for (String followee : followees) {
                        final String trimedFollowee = followee.trim();
                        if (!existsInSet(users, trimedFollowee)) {
                            final User user2 = new User();
                            user2.setName(trimedFollowee);
                            users.add(user2);
                        }
                        user.getFollows().add(trimedFollowee);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        return users;
    }

    private boolean isValidFormat(final String[] text) {
        return text.length == Constants.TWEET_SPLIT_COUNT;
    }

    public Boolean existsInSet(Set<User> users, String username) {
        return users.stream().anyMatch(user -> user.getName().equals(username));
    }

    public List<User> sortUsers(Set<User> users) {
        List<User> userList = new ArrayList<>(users);
        Collections.sort(userList);
        return userList;
    }
}
