package pl.edu.wat.airline.controller;

import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.airline.entity.User;
import pl.edu.wat.airline.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService users;

    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }

    @GetMapping("all_users")
    public Iterable<User> getAllUsers(){
        return users.findAll();
    }

    @GetMapping
    public Optional<User> getById(@RequestParam Long id) {
        return users.findById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return users.save(user);
    }

    @PutMapping
    public User updateUser(@RequestParam Long id, @RequestBody User userRequest) {
        return users.findById(id).map(user ->{
            user.setName(userRequest.getName());
            user.setSurname(userRequest.getSurname());
            user.setBirthdate(userRequest.getBirthdate());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setEmail(userRequest.getEmail());
            user.setLogin(userRequest.getLogin());
            user.setPassword(userRequest.getPassword());
            user.setSalt(userRequest.getSalt());
            return users.save(user);
        }).orElseThrow(() -> new ResourceRequestDeniedException("UserId " + id + " not found."));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return users.findById(id).map(user -> {
            users.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceRequestDeniedException("UserId " + id + " not found."));
    }
}
