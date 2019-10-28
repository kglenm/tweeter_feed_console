package main.com.feed.domain;

import java.util.*;

public class User implements Comparable{

    private String name;

    private Set<String> follows = new HashSet<>();

    public User() {}

    public User(String name, Set<String> follows) {
        this.name = name;
        this.follows = follows;
    }

    public Set<String> getFollows() {
        return follows;
    }

    public void setFollows(Set<String> follows) {
        this.follows = follows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((User) o).getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
