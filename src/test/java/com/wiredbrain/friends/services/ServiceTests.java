package com.wiredbrain.friends.services;

import com.wiredbrain.friends.models.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

    @Autowired
    FriendService friendService;

    @Test
    public void testCreateReadDelete() throws Exception{
        Friend friend = new Friend("Gordon", "Moore");

        friendService.save(friend);

        Iterable<Friend> friends = friendService.findAll();
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");

        friendService.deleteAll();
        Assertions.assertThat(friendService.findAll()).isEmpty();
    }
}
