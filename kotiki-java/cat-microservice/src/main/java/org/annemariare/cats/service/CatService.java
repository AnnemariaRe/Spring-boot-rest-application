package org.annemariare.cats.service;

import org.annemariare.cats.dto.CatDto;
import org.annemariare.cats.enums.Color;
import org.annemariare.cats.exception.EntityAlreadyExistsException;
import org.annemariare.cats.exception.EntityNotFoundException;

import java.util.List;

public interface CatService {
    void add(CatDto kotik) throws EntityAlreadyExistsException;
    List<CatDto> getAll(String username);
    CatDto getOne(Long id, String username);
    CatDto getSomeByName(String name, String username);
    List<CatDto> getSomeByBreed(String breed, String username);
    List<CatDto> getSomeByColor(Color color, String username);
    void delete(Long id) throws EntityNotFoundException;

}
