package com.wiredbrain.friends.services;

import com.wiredbrain.friends.models.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendService extends CrudRepository<Friend, Long> {
    public Iterable<Friend> findByFirstNameAndLastName(String firstName, String lastName);
    public Iterable<Friend> findByFirstName(String firstName);
    public Iterable<Friend> findByLastName(String lastName);
}
