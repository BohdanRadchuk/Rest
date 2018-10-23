package com.lab.restapp.restServices;

import com.lab.restapp.entity.Device;
import com.lab.restapp.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
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
        System.out.println(users);
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("get");
        return new ResponseEntity<List<User>>(this.users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        System.out.println("get one by id");
        if (id == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        int parsedId = Integer.parseInt(id);
        if (0 < parsedId && parsedId <= users.size()) {
            return new ResponseEntity<User>(this.users.get(parsedId - 1), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}/devices", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Device>> getUsersDevices(@PathVariable String id) {
        System.out.println("get one by id");
        if (id == null) {
            return new ResponseEntity<List<Device>>(HttpStatus.BAD_REQUEST);
        }
        int parsedId = Integer.parseInt(id);
        if (0 < parsedId && parsedId <= users.size()) {
            return new ResponseEntity<List<Device>>(this.users.get(parsedId - 1).getDevices(), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Device>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        this.users.add(user);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        this.users.set(user.getId() - 1, user);
        return new ResponseEntity<User>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        if (id == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        int parsedId = Integer.parseInt(id);
        if (0 < parsedId && parsedId <= users.size()) {
            users.remove(parsedId - 1);
            return new ResponseEntity<User>(HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
    }
}
