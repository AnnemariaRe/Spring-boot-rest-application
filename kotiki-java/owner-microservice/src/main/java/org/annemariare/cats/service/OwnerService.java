package org.annemariare.cats.service;

import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.dto.OwnerDto;
import org.annemariare.cats.exception.EntityAlreadyExistsException;
import org.annemariare.cats.exception.EntityNotFoundException;

import java.util.List;

public interface OwnerService {
    void add(OwnerDto owner) throws EntityAlreadyExistsException;
    List<OwnerDto> getAll();
    List<CatDto> getAllCats(Long id, String username);
    OwnerDto getOne(String name);
    OwnerDto getOne(Long id);
    OwnerDto getSomeByName(String name);
    void delete(Long id) throws EntityNotFoundException;
    void deleteAll();
}
