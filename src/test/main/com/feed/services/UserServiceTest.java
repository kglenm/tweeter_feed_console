package main.com.feed.services;


import main.com.feed.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UserServiceTest {

    private static Set<User> userSet = new HashSet<>();

    @BeforeAll
    static void setup() {
        Set<String> followees1 = new HashSet<>();
        followees1.add("Alan");
        followees1.add("Martin");
        Set<String> followees2 = new HashSet<>();
        followees2.add("Martin");
        User user1 = new User();
        user1.setName("Ward");
        user1.setFollows(followees1);
        User user2 = new User();
        user2.setName("Martin");
        user2.setFollows(new HashSet<>());
        User user3 = new User();
        user3.setName("Alan");
        user3.setFollows(followees2);
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
    }

    @DisplayName("Should return list of users when file with users exists")
    @Test
    void testGetUserSet() throws IOException {
        UserService userService = new UserService();
        Set<User> foundUserSet = userService.getUsers("user.txt");
        Assertions.assertNotNull(foundUserSet);
        Assertions.assertEquals(foundUserSet, userSet);
    }

    @DisplayName("Should return list of sorted users when provided with unsorted set of users")
    @Test
    void testSortingOfUserSet() {
        UserService userService = new UserService();
        List<User> sortedUsers = userService.sortUsers(userSet);
        boolean isSorted = isSorted(sortedUsers);
        Assertions.assertTrue(isSorted);
    }

    @DisplayName("Should return true if user exists in list")
    @Test
    void testFindingIfUserExists() {
        UserService userService = new UserService();
        boolean exists = userService.existsInSet(userSet, "Alan");
        Assertions.assertTrue(exists);
    }

    @DisplayName("Should return false if user does not exists in list")
    @Test
    void testFindingIfUserDoesntExists() {
        UserService userService = new UserService();
        boolean exists = userService.existsInSet(userSet, "Glen");
        Assertions.assertFalse(exists);
    }

    private static <T extends Comparable> boolean isSorted(List<User> listOfT) {
        User previous = null;
        for (User t: listOfT) {
            if (previous != null && t.compareTo(previous) < 0) return false;
            previous = t;
        }
        return true;
    }
}
