package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dao.KotikRepo;
import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.dto.OwnerDto;
import org.annemariare.kotiki.entity.KotikEntity;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.annemariare.kotiki.dto.KotikConverter.dtoToEntity;
import static org.annemariare.kotiki.dto.KotikConverter.entityToDto;

@Service
public class KotikService {

    private final KotikRepo kotikRepo;

    @Autowired
    public KotikService(KotikRepo kotikRepo) {
        this.kotikRepo = kotikRepo;
    }

    public void add(KotikDto kotik) throws EntityAlreadyExistsException {
        if (kotikRepo.findByName(kotik.getName()) != null) {
            throw new EntityAlreadyExistsException();
        }
        kotikRepo.save(dtoToEntity(kotik));
    }

    public List<KotikDto> getAll() {
        List<KotikEntity> kotiki = kotikRepo.findAll();

        List<KotikDto> dto = new ArrayList<>();
        for (var entity : kotiki) {
            dto.add(entityToDto(entity));
        }
        return dto;
    }

    public KotikDto getOne(Long id) {
        KotikEntity kotik = kotikRepo.findById(id);
        return entityToDto(kotik);
    }

    public KotikDto getSomeByName(String name) {
        KotikEntity kotik = kotikRepo.findByName(name);
        return entityToDto(kotik);
    }

    public KotikDto getSomeByBreed(String breed) {
        KotikEntity kotik = kotikRepo.findByBreed(breed);
        return entityToDto(kotik);
    }

    public KotikDto getSomeByColor(Color color) {
        KotikEntity kotik = kotikRepo.findByColor(color);
        return entityToDto(kotik);
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
