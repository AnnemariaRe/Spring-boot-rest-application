package org.annemariare.cats.api;

import org.annemariare.cats.dto.OwnerDto;
import org.annemariare.cats.exception.EntityAlreadyExistsException;
import org.annemariare.cats.rabbitmq.OwnerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/owners")
public class OwnerController {
    @Autowired
    private OwnerSender sender;

    @PostMapping("/save")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> save(@RequestBody OwnerDto owner) {
        try {
            sender.send(owner);
            return ResponseEntity.ok("Owner is successfully added");
        } catch(EntityAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/me")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getOwner(Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "id/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sender.send_id(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "name/{name}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getOwnerByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(sender.send(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/all")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(sender.send("all"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/cats")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> findAllCats(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(id, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteOwner(@PathVariable Long id) {
        try {
            sender.send(id);
            ResponseEntity.ok("Owner is successfully deleted :(");
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error -.-");
        }
    }

}
