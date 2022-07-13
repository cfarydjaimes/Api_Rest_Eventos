package cris.dev.eventos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import cris.dev.eventos.Models.Fiesta;
import cris.dev.eventos.Repositories.FiestaRepository;

@RestController
@RequestMapping("/api/fiestas")
public class FiestaController {
    
    @Autowired
    private FiestaRepository fiestaRepository;

    @GetMapping
    public ResponseEntity<Collection<Fiesta>> listarFiestas(){
        return new ResponseEntity<>(fiestaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fiesta> obtenerFiestaPorId(@PathVariable Long id){

        Fiesta fiesta = fiestaRepository.findById(id).orElseThrow();
        if(fiesta != null){
            return new ResponseEntity<>(fiestaRepository.findById(id).orElseThrow(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarFiesta(@RequestBody Fiesta Fiesta){
        return new ResponseEntity<>(fiestaRepository.save(Fiesta), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFiesta(@PathVariable Long id){
        
        Optional<Fiesta> FiestaOptional = fiestaRepository.findById(id);
        if(FiestaOptional.isPresent()){
            fiestaRepository.delete(FiestaOptional.get());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

}
