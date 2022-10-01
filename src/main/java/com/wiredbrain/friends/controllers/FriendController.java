package com.wiredbrain.friends.controllers;

import com.wiredbrain.friends.models.Friend;
import com.wiredbrain.friends.services.FriendService;
import com.wiredbrain.friends.utils.FieldErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FriendController {

    private FriendService friendService;

    @PostMapping("/friends")
    public Friend create(@Valid @RequestBody Friend friend) throws ValidationException {
        return friendService.save(friend);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage>  fieldErrorMessages = fieldErrors.stream().map( fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @GetMapping("/friends")
    public Iterable<Friend> read() {
        return friendService.findAll();
    }

    @PutMapping("/friends")
    public ResponseEntity<Friend> update(@RequestBody Friend friend) {
        if (friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity<Friend>(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity<Friend>(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friends/{id}")
    public void delete(@PathVariable Long id) {
        friendService.deleteById(id);
    }

    @GetMapping("/friends/{id}")
    public Optional<Friend> getOne(@PathVariable Long id) {
        return friendService.findById(id);
    }

    @GetMapping("/friends/search")
    public Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName, @RequestParam(value = "last", required = false) String lastName) {
        if ( firstName != null && lastName != null)
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        else if (firstName != null)
            return friendService.findByFirstName(firstName);
        else if (lastName != null)
            return friendService.findByLastName(lastName);
        else
            return friendService.findAll();
    }
}
