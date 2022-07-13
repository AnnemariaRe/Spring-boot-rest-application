package org.annemariare.cats.service;

import org.annemariare.cats.converter.CatConverter;
import org.annemariare.cats.converter.OwnerConverter;
import org.annemariare.cats.dao.OwnerRepo;
import org.annemariare.cats.dao.UserRepo;
import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.dto.OwnerDto;
import org.annemariare.cats.entity.CatEntity;
import org.annemariare.cats.entity.OwnerEntity;
import org.annemariare.cats.entity.UserEntity;
import org.annemariare.cats.enums.Role;
import org.annemariare.cats.exception.EntityAlreadyExistsException;
import org.annemariare.cats.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepo ownerRepo;
    private final UserRepo userRepo;
    private final OwnerConverter convert;
    private final CatConverter catConvert;

    @Autowired
    public OwnerServiceImpl(OwnerRepo ownerRepo, UserRepo userRepo, OwnerConverter convert, CatConverter catConvert) {
        this.ownerRepo = ownerRepo;
        this.userRepo = userRepo;
        this.convert = convert;
        this.catConvert = catConvert;
    }

    public void add(OwnerDto owner) throws EntityAlreadyExistsException {
        if (ownerRepo.findByName(owner.getName()) != null) {
            throw new EntityAlreadyExistsException();
        }

        ownerRepo.save(convert.dtoToEntity(owner));
    }

    public List<OwnerDto> getAll() {
        List<OwnerEntity> owners = ownerRepo.findAll();
        List<OwnerDto> dto = new ArrayList<>();
        for (var entity : owners) {
            dto.add(convert.entityToDto(entity));
        }
        return dto;
    }

    public List<CatDto> getAllCats(Long id, String username) {
        OwnerEntity owner = ownerRepo.findById(id);
        UserEntity user = userRepo.findByUsername(username);
        if (Objects.equals(owner.getId(), user.getOwner().getId()) || user.getRole() == Role.ROLE_ADMIN) {
            List<CatEntity> cats = owner.getCats();
            List<CatDto> dto = new ArrayList<>();
            for (var entity : cats) dto.add(catConvert.entityToDto(entity));

            return dto;
        }

        throw new EntityNotFoundException();
    }

    public OwnerDto getOne(String username) {
        OwnerEntity owner = userRepo.findByUsername(username).getOwner();
        return convert.entityToDto(owner);
    }

    public OwnerDto getOne(Long id) {
        OwnerEntity owner = ownerRepo.findById(id);
        return convert.entityToDto(owner);
    }

    public OwnerDto getSomeByName(String name) {
        OwnerEntity owner = ownerRepo.findByName(name);
        return convert.entityToDto(owner);
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
