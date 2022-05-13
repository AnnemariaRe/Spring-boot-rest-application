package org.annemariare.kotiki.api;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.service.KotikServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/kotiki")
public class KotikController {

    private final KotikServiceImpl service;

    @Autowired
    public KotikController(KotikServiceImpl service) {
        this.service = service;
    }

    @PostMapping(path = "/save")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> save(@RequestBody KotikDto kotik) {
        try {
            service.add(kotik);
            return ResponseEntity.ok("Kotik is successfully added");
        } catch(EntityAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getKotikById(@PathVariable Long id, Principal principal) {
        try {
            return ResponseEntity.ok(service.getOne(id, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "name/{name}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getKotikByName(@PathVariable String name, Principal principal) {
        try {
            return ResponseEntity.ok(service.getSomeByName(name, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "breed/{breed}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getKotikiByBreed(@PathVariable String breed, Principal principal) {
        try {
            return ResponseEntity.ok(service.getSomeByBreed(breed, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "color/{color}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getKotikiByColor(@PathVariable Color color, Principal principal) {
        try {
            return ResponseEntity.ok(service.getSomeByColor(color, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> findAll(Principal principal) {
        try {
            return ResponseEntity.ok(service.getAll(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @DeleteMapping(value = "id/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteKotik(@PathVariable Long id) {
        try {
            service.delete(id);
            ResponseEntity.ok("Kotik is successfully deleted :(");
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error -.-");
        }
    }
}
