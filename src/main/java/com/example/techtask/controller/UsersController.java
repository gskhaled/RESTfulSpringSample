package com.example.techtask.controller;

import com.example.techtask.exceptions.ResourceNotFoundException;
import com.example.techtask.model.User;
import com.example.techtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // add new user
    @PostMapping
    public @ResponseBody String addNewUser(
            @RequestParam
            String name,
            @RequestParam
            String email) {

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        User saved = userRepository.save(n);
        return String.format("Successfully saved new user with id: [%d]", saved.getId());
    }

    // get all users
    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public @ResponseBody User getUserById(
            @PathVariable("id")
            Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id [%s] does not exist!", id));
        } else {
            return user.get();
        }
    }

    // update user by id
    @PutMapping("/{id}")
    public @ResponseBody String updateUserById(
            @PathVariable("id")
            Integer id,
            @RequestParam
            Optional<String> name,
            @RequestParam
            Optional<String> email) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id [%s] does not exist!", id));
        }

        User updatedUser = new User();
        updatedUser.setName(name.orElse(existingUser.get().getName())); // keep name as it is if not provided
        updatedUser.setEmail(email.orElse(existingUser.get().getEmail())); // keep email as it is if not provided
        updatedUser.setId(existingUser.get().getId());

        User saved = userRepository.save(updatedUser);
        return String.format("Successfully updated user with id: [%d]", saved.getId());
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public @ResponseBody String deleteUserById(
            @PathVariable("id")
            Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id [%s] does not exist!", id));
        }

        userRepository.deleteById(id);
        return String.format("Successfully deleted user with id: [%d]", id);
    }
}
