package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;

import java.util.List;

public interface KotikServiceInterface {
    void add(KotikDto kotik) throws EntityAlreadyExistsException;
    List<KotikDto> getAll();
    KotikDto getOne(Long id);
    KotikDto getSomeByName(String name);
    KotikDto getSomeByBreed(String breed);
    KotikDto getSomeByColor(Color color);
    void delete(Long id) throws EntityNotFoundException;

}
