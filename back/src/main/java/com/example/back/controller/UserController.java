package com.example.back.controller;

import com.example.back.model.User;
import com.example.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdate(@RequestBody User partialUpdate, @PathVariable Integer id) {
        try {
            User existingUser = userService.getUser(id);

            if (partialUpdate.getFirstName() != null) {
                existingUser.setFirstName(partialUpdate.getFirstName());
            }

            if (partialUpdate.getLastName() != null) {
                existingUser.setLastName(partialUpdate.getLastName());
            }

            // Vous pouvez ajouter d'autres propriétés à mettre à jour de manière sélective

            userService.saveUser(existingUser);
            return new ResponseEntity<>("User with ID " + id + " updated successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user with ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
