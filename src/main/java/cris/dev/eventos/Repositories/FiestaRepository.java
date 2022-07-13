package cris.dev.eventos.Repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import cris.dev.eventos.Models.Fiesta;

public interface FiestaRepository extends CrudRepository <Fiesta, Long>{

    Collection<Fiesta> findAll();
    
}
