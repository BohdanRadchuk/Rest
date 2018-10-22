package com.lab.restapp.restServices;

import com.lab.restapp.entity.Device;
import com.lab.restapp.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController("/users")
public class UserController {

    private List<User> users = generateUsers();

    private List<User> generateUsers() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Device> devices = new ArrayList<>();
        devices.add(new Device(1, "Computer"));
        devices.add(new Device(2, "chair"));
        users.add(new User(1, "Ivan", devices));
        users.add(new User(2, "Sergiy", devices));
        devices.add(new Device(3, "table"));
        users.add(new User(3, "Ostap", devices));
        return users;
    }

/*
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") String customerId) {
        if (customerId == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        customerId.replace("users?", "");
        int id = Integer.parseInt(customerId);
        System.out.println(id);
        if (id < 0 || id > this.users.size()){
            System.out.println(false);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<User>(users.get(id-1), HttpStatus.OK);
    }

*/

 /*   @GetMapping
    public User getUser() {
        List<Device> devices = new ArrayList<>();
        devices.add(new Device(2, "computer"));
        return new User(1, "asd", devices);
    }*/

    @GetMapping
    public List<User> getAllUsers() {
        return this.users;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        this.users.add(user);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }
}
