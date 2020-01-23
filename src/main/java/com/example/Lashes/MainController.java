package com.example.Lashes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
public class MainController {

    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;


    @GetMapping(path="/users")
    Iterable<User> getAllMovies() {
        return userRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public User findById(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/movies")
    public ResponseEntity<User> newMovie(@RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<User> updateMovie(@RequestBody User user, @PathVariable Integer id){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
