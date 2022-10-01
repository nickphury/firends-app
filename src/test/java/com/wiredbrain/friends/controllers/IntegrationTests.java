package com.wiredbrain.friends.controllers;

import com.wiredbrain.friends.models.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

    @Autowired
    FriendController friendController;

    @Test
    public void testCreateReadDelete() throws ValidationException {
        Friend friend = new Friend("Gordon", "Moore");

        Friend friendResult = friendController.create(friend);
        Iterable<Friend> friends = friendController.read();
        long length = friends.spliterator().getExactSizeIfKnown();
        Assertions.assertThat(friends).last().hasFieldOrPropertyWithValue("firstName", "Gordon");

        friendController.delete(friendResult.getId());
        Assertions.assertThat(friendController.read().spliterator().getExactSizeIfKnown()).isEqualTo(length - 1);
    }
}
