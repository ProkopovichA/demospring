package com.prokopovich.demospring.service;


import com.prokopovich.demospring.entity.UserEntity;
import com.prokopovich.demospring.exception.UserAlredyExistException;
import com.prokopovich.demospring.exception.UserNotFoundException;
import com.prokopovich.demospring.model.User;
import com.prokopovich.demospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity registration(UserEntity user) throws UserAlredyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw  new UserAlredyExistException("Username already exists");
            //return ResponseEntity.badRequest().body("Username already exists");
        }

        return  userRepository.save(user); //ResponseEntity.ok("User saved successfully");
    }

    public User findUserById(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        return User.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }


}
