package com.example.formation.controller;

import com.example.formation.dto.UserDto;
import com.example.formation.entity.User;
import com.example.formation.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @GetMapping("/AfficherTousUser")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userFacade.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/creerUnNouveauUser")
    public ResponseEntity<UserDto> createNewPays(@RequestBody UserDto userDto) {
        UserDto createUser = userFacade.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @DeleteMapping("/supprimerUnUser/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userFacade.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/updatePersonneById/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedPersonne = userFacade.updateUser(userDto);
        if (updatedPersonne != null) {
            return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/AfficherUnUser/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id")  Long id) {
        UserDto user = userFacade.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
