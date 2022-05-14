package org.annemariare.kotiki.service;

import org.annemariare.kotiki.converter.KotikConverter;
import org.annemariare.kotiki.converter.OwnerConverter;
import org.annemariare.kotiki.dao.OwnerRepo;
import org.annemariare.kotiki.dao.UserRepo;
import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.entity.OwnerEntity;
import org.annemariare.kotiki.entity.UserEntity;
import org.annemariare.kotiki.enums.Role;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;
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
    private final KotikConverter kotikConvert;

    @Autowired
    public OwnerServiceImpl(OwnerRepo ownerRepo, UserRepo userRepo, OwnerConverter convert, KotikConverter kotikConvert) {
        this.ownerRepo = ownerRepo;
        this.userRepo = userRepo;
        this.convert = convert;
        this.kotikConvert = kotikConvert;
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

    public List<KotikDto> getAllKotiki(Long id, String username) {
        OwnerEntity owner = ownerRepo.findById(id);
        UserEntity user = userRepo.findByUsername(username);
        if (Objects.equals(owner.getId(), user.getOwner().getId()) || user.getRole() == Role.ROLE_ADMIN) {
            List<KotikEntity> kotiki = owner.getKotiki();
            List<KotikDto> dto = new ArrayList<>();
            for (var entity : kotiki) dto.add(kotikConvert.entityToDto(entity));

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
