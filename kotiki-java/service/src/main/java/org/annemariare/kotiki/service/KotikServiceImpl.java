package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dao.KotikRepo;
import org.annemariare.kotiki.dao.UserRepo;
import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.entity.UserEntity;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.enums.Role;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.annemariare.kotiki.converter.KotikConverter.dtoToEntity;
import static org.annemariare.kotiki.converter.KotikConverter.entityToDto;

@Service
public class KotikServiceImpl implements KotikService {

    private final KotikRepo kotikRepo;
    private final UserRepo userRepo;

    @Autowired
    public KotikServiceImpl(KotikRepo kotikRepo, UserRepo userRepo) {
        this.kotikRepo = kotikRepo;
        this.userRepo = userRepo;
    }

    public void add(KotikDto kotik) throws EntityAlreadyExistsException {
        if (kotikRepo.findByName(kotik.getName()) != null) {
            throw new EntityAlreadyExistsException();
        }
        kotikRepo.save(dtoToEntity(kotik));
    }

    public List<KotikDto> getAll(String username) {
        UserEntity user = userRepo.findByUsername(username);

        List<KotikEntity> kotiki = kotikRepo.findAllByOwner(user.getOwner());
        List<KotikDto> dto = new ArrayList<>();
        for (var entity : kotiki) dto.add(entityToDto(entity));

        if (user.getRole() == Role.ROLE_ADMIN) {
            List<KotikEntity> kotiki2 = kotikRepo.findAll();
            List<KotikDto> dto2 = new ArrayList<>();
            for (var entity : kotiki2) dto2.add(entityToDto(entity));

            return dto2;
        }
        return dto;
    }

    public KotikDto getOne(Long id, String username) {
        KotikEntity kotik = kotikRepo.findById(id);
        UserEntity user = userRepo.findByUsername(username);

        if (Objects.equals(user.getOwner().getId(), kotik.getOwner().getId())
                || user.getRole() == Role.ROLE_ADMIN) {
            return entityToDto(kotik);
        }

        throw new EntityNotFoundException();
    }

    public KotikDto getSomeByName(String name, String username) {
        KotikEntity kotik = kotikRepo.findByName(name);
        UserEntity user = userRepo.findByUsername(username);

        if (Objects.equals(user.getOwner().getId(), kotik.getOwner().getId())
                || user.getRole() == Role.ROLE_ADMIN) {
            return entityToDto(kotik);
        }

        throw new EntityNotFoundException();
    }

    public List<KotikDto> getSomeByBreed(String breed, String username) {
        List<KotikEntity> kotiki = kotikRepo.findByBreed(breed);
        List<KotikDto> dto = new ArrayList<>();
        for (var entity : kotiki) dto.add(entityToDto(entity));

        UserEntity user = userRepo.findByUsername(username);
        if (user.getRole() == Role.ROLE_ADMIN) return dto;
        else if (user.getOwner() != null) {
            dto.removeIf(d -> !Objects.equals(d.getOwner().getId(), user.getOwner().getId()));
            return dto;
        }

        throw new EntityNotFoundException();
    }

    public List<KotikDto> getSomeByColor(Color color, String username) {
        List<KotikEntity> kotiki = kotikRepo.findByColor(color);
        List<KotikDto> dto = new ArrayList<>();
        for (var entity : kotiki) dto.add(entityToDto(entity));

        UserEntity user = userRepo.findByUsername(username);
        if (user.getRole() == Role.ROLE_ADMIN) return dto;
        else if (user.getOwner() != null) {
            dto.removeIf(d -> !Objects.equals(d.getOwner().getId(), user.getOwner().getId()));
            return dto;
        }

        throw new EntityNotFoundException();
    }

    public void delete(Long id) throws EntityNotFoundException {
        if(!kotikRepo.existsById(id)) {
            throw new EntityNotFoundException();
        }
        kotikRepo.deleteById(id);
    }

    public void deleteAll() {
        kotikRepo.deleteAll();
    }
}
