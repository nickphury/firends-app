package com.wiredbrain.friends;

import com.wiredbrain.friends.models.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SystemTests {

    @Test
    public void testCreateReadDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8888/friends";

        Friend friend = new Friend("Gordon", "Moore");
        ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);

        Friend[] friends = restTemplate.getForObject(url, Friend[].class);
        int length = friends.length;
        Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("Gordon");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url, Friend[].class).length).isEqualTo(length - 1);
    }
}
