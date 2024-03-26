package com.fyp.hca.services;

import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.UsersRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class UsersService {
    UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(Users users) {
        usersRepository.save(users);
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return new ArrayList<Users>(usersRepository.findAll());
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }

    public void updateUsers(Users users) {
        usersRepository.save(users);
    }

    public Optional<Users> isValidUser(String email, String password) {
        return usersRepository.findByEmailAndPassword(email,password);
    }

}
