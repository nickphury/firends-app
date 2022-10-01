package com.wiredbrain.friends;

import com.wiredbrain.friends.controllers.FriendController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class FriendsApplicationTests {
	@Autowired
	FriendController friendController;

	@Test
	void contextLoads() {
		Assert.notNull(friendController, "Friend controller");
	}
}
