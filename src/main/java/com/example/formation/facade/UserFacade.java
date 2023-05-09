package com.example.formation.facade;

import com.example.formation.dto.UserDto;
import com.example.formation.entity.User;
import com.example.formation.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFacade {
    @Autowired
    private UserServiceImpl userServiceImpl;

    public List<UserDto> getAllUsers() {
        List<User> users = userServiceImpl.getAllUsers();
        return users.stream()
                .map(user -> convertToDto(user))
                .collect(Collectors.toList());
    }
    public UserDto getUserById(Long id) {
        User user = userServiceImpl.getUserById(id);
        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDTO) {
        User user = convertToEntity(userDTO);
        user = userServiceImpl.createUser(user);
        return convertToDto(user);
    }

    public UserDto updateUser(UserDto userDTO) {
        User user = convertToEntity(userDTO);
        user = userServiceImpl.updateUser(user);
        return convertToDto(user);
    }

    public void deleteUser(Long id) {
        userServiceImpl.deleteUser(id);
    }

     private UserDto convertToDto(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setNom(user.getNom());
        userDTO.setPrenom(user.getPrenom());
        userDTO.setSexe(user.getSexe());
        userDTO.setAge(user.getAge());
        return userDTO;
    }

    private User convertToEntity(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setSexe(userDTO.getSexe());
        user.setAge(userDTO.getAge());
        return user;
    }
}
