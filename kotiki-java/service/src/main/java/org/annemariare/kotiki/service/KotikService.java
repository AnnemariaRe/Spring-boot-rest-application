package org.annemariare.kotiki.service;

import org.annemariare.kotiki.dto.KotikDto;
import org.annemariare.kotiki.enums.Color;
import org.annemariare.kotiki.exception.EntityAlreadyExistsException;
import org.annemariare.kotiki.exception.EntityNotFoundException;

import java.util.List;

public interface KotikService {
    void add(KotikDto kotik) throws EntityAlreadyExistsException;
    List<KotikDto> getAll(String username);
    KotikDto getOne(Long id, String username);
    KotikDto getSomeByName(String name, String username);
    List<KotikDto> getSomeByBreed(String breed, String username);
    List<KotikDto> getSomeByColor(Color color, String username);
    void delete(Long id) throws EntityNotFoundException;

}
