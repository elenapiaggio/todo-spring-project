package com.competidor.todolist.service.impl;

import com.competidor.todolist.entity.User;
import com.competidor.todolist.repository.IUserRepository;
import com.competidor.todolist.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public Boolean deleteUserById(String userId) {
        return getUserById(userId).map(user -> {
            userRepository.deleteById(userId);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean update(User user, String userId) {
        return getUserById(userId).map(userToModify -> {
            user.setId(userId);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

}