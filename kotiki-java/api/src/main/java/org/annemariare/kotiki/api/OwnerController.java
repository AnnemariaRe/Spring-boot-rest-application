package org.annemariare.kotiki.api;

import org.annemariare.kotiki.entity.OwnerEntity;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/owners")
public class OwnerController {
    private final OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody OwnerEntity owner) {
        try {
            service.add(owner);
            return ResponseEntity.ok("Owner is successfully added");
        } catch(EntityAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity getOwnerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error -.-");
        }
    }

    @GetMapping(value = "name/{name}")
    public ResponseEntity getOwnerByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(service.getSomeByName(name));
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
public void deletePost(@PathVariable Long id) {
    try {
        service.delete(id);
        ResponseEntity.ok("Owner is successfully deleted :(");
    } catch (Exception e) {
        ResponseEntity.badRequest().body("Error -.-");
    }
}

}
