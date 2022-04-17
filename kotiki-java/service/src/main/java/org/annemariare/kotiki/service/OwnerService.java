package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.OwnerEntity;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;

import java.util.List;

public interface OwnerService {
    void add(OwnerEntity owner) throws EntityAlreadyExistsException;
    List<OwnerDto> getAll();
    OwnerDto getOne(Long id);
    OwnerDto getSomeByName(String name);
    void delete(Long id) throws EntityNotFoundException;

}
