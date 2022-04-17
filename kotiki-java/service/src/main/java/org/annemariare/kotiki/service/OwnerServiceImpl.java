package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dao.OwnerRepo;
import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.OwnerEntity;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.annemariare.kotiki.dto.OwnerConverter.entityToDto;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepo ownerRepo;

    @Autowired
    public OwnerServiceImpl(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    public void add(OwnerEntity owner) throws EntityAlreadyExistsException {
        if (ownerRepo.findByName(owner.getName()) != null) {
            throw new EntityAlreadyExistsException();
        }
        ownerRepo.save(owner);
    }

    public List<OwnerDto> getAll() {
        List<OwnerEntity> owners = ownerRepo.findAll();
        List<OwnerDto> dto = new ArrayList<>();
        for (var entity : owners) {
            dto.add(entityToDto(entity));
        }
        return dto;
    }

    public OwnerDto getOne(Long id) {
        OwnerEntity owner = ownerRepo.findById(id);
        return entityToDto(owner);
    }

    public OwnerDto getSomeByName(String name) {
        OwnerEntity owner = ownerRepo.findByName(name);
        return entityToDto(owner);
    }

    public void delete(Long id) throws EntityNotFoundException {
        if(!ownerRepo.existsById(id)) {
            throw new EntityNotFoundException();
        }
        ownerRepo.deleteById(id);
    }

    public void deleteAll() {
        ownerRepo.deleteAll();
    }
}
