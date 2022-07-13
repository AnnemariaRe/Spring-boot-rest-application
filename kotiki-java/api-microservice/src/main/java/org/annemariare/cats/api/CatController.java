package org.annemariare.cats.api;

import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.enums.Color;
import org.annemariare.cats.exception.EntityAlreadyExistsException;
import org.annemariare.cats.rabbitmq.CatSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/cats")
public class CatController {
    @Autowired
    private CatSender sender;

    @PostMapping(path = "/save")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> save(@RequestBody CatDto cat) {
        try {
            sender.send(cat);
            return ResponseEntity.ok("Cat is successfully added");
        } catch(EntityAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getCatById(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(id, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "name/{name}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getCatByName(@PathVariable String name, Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(name, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "breed/{breed}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getCatsByBreed(@PathVariable String breed, Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(breed, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "color/{color}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getCatsByColor(@PathVariable Color color, Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(color, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll(Principal principal) {
        try {
            return ResponseEntity.ok(sender.send(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @DeleteMapping(value = "id/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteCat(@PathVariable Long id) {
        try {
            sender.send(id);
            ResponseEntity.ok("Cat is successfully deleted :(");
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error -.-");
        }
    }
}
