package com.fyp.hca.services;

import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
        return Optional.ofNullable(usersRepository.findByEmailAndPassword(email, password).orElse(null));
    }

    public List<Users> getallUsers(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Users> pagedResult = usersRepository.findAll(paging);
        return pagedResult.toList();
    }
    public List<Users> getallUsers2() {
       return new ArrayList<Users>(usersRepository.findAll());
    }

}
