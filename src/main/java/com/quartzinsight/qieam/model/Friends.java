package com.quartzinsight.qieam.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Friends {
    private List<Friend> friends;

    public Friends() {
        friends = new ArrayList<Friend>();
    }

    public void addFriend(Friend friend) {
        friends.add(friend);
    }

    public List<Friend> getFriends() {
        return Collections.unmodifiableList(friends);
    }
}