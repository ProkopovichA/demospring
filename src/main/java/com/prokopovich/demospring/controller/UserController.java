package com.prokopovich.demospring.controller;

import com.prokopovich.demospring.entity.UserEntity;
import com.prokopovich.demospring.exception.UserAlredyExistException;
import com.prokopovich.demospring.exception.UserNotFoundException;
import com.prokopovich.demospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registation(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User saved successfully");
        }catch (UserAlredyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Application error");
        }
    }


    @GetMapping
    public ResponseEntity findUserById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.findUserById(id));
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Application error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));

        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Application error");
        }
    }

}
