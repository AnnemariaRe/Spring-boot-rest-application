package org.annemariare.kotiki.api;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.service.KotikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/kotiki")
public class KotikController {

    private final KotikService service;

    @Autowired
    public KotikController(KotikService service) {
        this.service = service;
    }

    @PostMapping(path = "/save", consumes = {"application/json"})
    public ResponseEntity save(@RequestBody KotikDto kotik) {
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
    public ResponseEntity getKotikById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "name/{name}")
    public ResponseEntity getKotikiByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(service.getSomeByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "breed/{breed}")
    public ResponseEntity getKotikiByBreed(@PathVariable String breed) {
        try {
            return ResponseEntity.ok(service.getSomeByBreed(breed));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "color/{color}")
    public ResponseEntity getKotikiByColor(@PathVariable Color color) {
        try {
            return ResponseEntity.ok(service.getSomeByColor(color));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteKotik(@PathVariable Long id) {
        try {
            service.delete(id);
            ResponseEntity.ok("Kotik is successfully deleted :(");
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Error -.-");
        }
    }
}
