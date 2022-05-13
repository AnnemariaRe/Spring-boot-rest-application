package org.annemariare.kotiki.api;

import org.annemariare.kotiki.dto.UserDto;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserDto user) {
        try {
            service.register(user);
            return ResponseEntity.ok("User is successfully registered");
        } catch(EntityAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }
}
