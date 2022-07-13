package cris.dev.eventos.Repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import cris.dev.eventos.Models.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long> {

    Collection<Persona> findAll();
}
