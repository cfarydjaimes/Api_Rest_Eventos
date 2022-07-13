package cris.dev.eventos.Controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cris.dev.eventos.Models.Fiesta;
import cris.dev.eventos.Models.Persona;
import cris.dev.eventos.Repositories.PersonaRepository;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
	public ResponseEntity<Collection<Persona>> listarPersonas(){
		return new ResponseEntity<>(personaRepository.findAll(),HttpStatus.OK);
	}

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id){

        Persona persona = personaRepository.findById(id).orElseThrow();
        if(persona != null){
            return new ResponseEntity<>(personaRepository.findById(id).orElseThrow(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarPersona(@RequestBody Persona persona){
        return new ResponseEntity<>(personaRepository.save(persona), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id){
        
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if(personaOptional.isPresent()){
            personaRepository.delete(personaOptional.get());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/fiestas")
    public ResponseEntity<Collection<Fiesta>> listarFiestas(@PathVariable Long id){
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if(personaOptional.isPresent()){
            personaRepository.delete(personaOptional.get());
            return new ResponseEntity<>(personaOptional.get().getFiestas(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}
