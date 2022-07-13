package org.annemariare.cats.service;


import org.annemariare.cats.converter.CatConverter;
import org.annemariare.cats.dao.CatRepo;
import org.annemariare.cats.dao.UserRepo;
import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.entity.CatEntity;
import org.annemariare.cats.entity.UserEntity;
import org.annemariare.cats.enums.Color;
import org.annemariare.cats.enums.Role;
import org.annemariare.cats.exception.EntityAlreadyExistsException;
import org.annemariare.cats.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepo catRepo;
    private final UserRepo userRepo;
    private final CatConverter convert;

    @Autowired
    public CatServiceImpl(CatRepo catRepo, UserRepo userRepo, CatConverter convert) {
        this.catRepo = catRepo;
        this.userRepo = userRepo;
        this.convert = convert;
    }

    public void add(CatDto cat) throws EntityAlreadyExistsException {
        if (catRepo.findByName(cat.getName()) != null) {
            throw new EntityAlreadyExistsException();
        }
        catRepo.save(convert.dtoToEntity(cat));
    }

    public List<CatDto> getAll(String username) {
        UserEntity user = userRepo.findByUsername(username);

        if (user.getRole() == Role.ROLE_ADMIN) {
            List<CatEntity> cats2 = catRepo.findAll();
            List<CatDto> dto2 = new ArrayList<>();
            for (var entity : cats2) dto2.add(convert.entityToDto(entity));

            return dto2;
        } else {
            List<CatEntity> cats = catRepo.findAllByOwner(user.getOwner());
            List<CatDto> dto = new ArrayList<>();
            for (var entity : cats) dto.add(convert.entityToDto(entity));
            return dto;
        }
    }

    public CatDto getOne(Long id, String username) {
        CatEntity cat = catRepo.findById(id);
        UserEntity user = userRepo.findByUsername(username);

        if (Objects.equals(user.getOwner().getId(), cat.getOwner().getId())
                || user.getRole() == Role.ROLE_ADMIN) {
            return convert.entityToDto(cat);
        }

        throw new EntityNotFoundException();
    }

    public CatDto getSomeByName(String name, String username) {
        CatEntity cat = catRepo.findByName(name);
        UserEntity user = userRepo.findByUsername(username);

        if (Objects.equals(user.getOwner().getId(), cat.getOwner().getId())
                || user.getRole() == Role.ROLE_ADMIN) {
            return convert.entityToDto(cat);
        }

        throw new EntityNotFoundException();
    }

    public List<CatDto> getSomeByBreed(String breed, String username) {
        List<CatEntity> cats = catRepo.findByBreed(breed);
        List<CatDto> dto = new ArrayList<>();
        for (var entity : cats) dto.add(convert.entityToDto(entity));

        UserEntity user = userRepo.findByUsername(username);
        if (user.getRole() == Role.ROLE_ADMIN) return dto;
        else if (user.getOwner() != null) {
            dto.removeIf(d -> !Objects.equals(d.getOwner().getId(), user.getOwner().getId()));
            return dto;
        }

        throw new EntityNotFoundException();
    }

    public List<CatDto> getSomeByColor(Color color, String username) {
        List<CatEntity> cats = catRepo.findByColor(color);
        List<CatDto> dto = new ArrayList<>();
        for (var entity : cats) dto.add(convert.entityToDto(entity));

        UserEntity user = userRepo.findByUsername(username);
        if (user.getRole() == Role.ROLE_ADMIN) return dto;
        else if (user.getOwner() != null) {
            dto.removeIf(d -> !Objects.equals(d.getOwner().getId(), user.getOwner().getId()));
            return dto;
        }

        throw new EntityNotFoundException();
    }

    public void delete(Long id) throws EntityNotFoundException {
        if(!catRepo.existsById(id)) {
            throw new EntityNotFoundException();
        }
        catRepo.deleteById(id);
    }

    public void deleteAll() {
        catRepo.deleteAll();
    }
}
