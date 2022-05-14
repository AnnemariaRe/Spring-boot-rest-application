package org.annemariare.kotiki.api;

import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/owners")
public class OwnerController {
    private final OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @PostMapping("/save")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> save(@RequestBody OwnerDto owner) {
        try {
            service.add(owner);
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
            return ResponseEntity.ok(service.getOne(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "id/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "name/{name}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getOwnerByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(service.getSomeByName(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/all")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/kotiki")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> findAllKotiki(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(service.getAllKotiki(id, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteOwner(@PathVariable Long id) {
        try {
            service.delete(id);
            ResponseEntity.ok("Owner is successfully deleted :(");
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error -.-");
        }
    }

}
