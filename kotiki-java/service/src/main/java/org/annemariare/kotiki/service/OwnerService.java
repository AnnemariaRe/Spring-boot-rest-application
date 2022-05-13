package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;

import java.util.List;

public interface OwnerService {
    void add(OwnerDto owner) throws EntityAlreadyExistsException;
    List<OwnerDto> getAll();
    List<KotikDto> getAllKotiki(Long id, String username);
    OwnerDto getOne(String name);
    OwnerDto getOne(Long id);
    OwnerDto getSomeByName(String name);
    void delete(Long id) throws EntityNotFoundException;
    void deleteAll();
}
