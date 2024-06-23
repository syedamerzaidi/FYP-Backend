package com.fyp.hca.services;

import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(Users users) {
        usersRepository.save(users);
    }

    public void saveaddNewUsersWithImg(Users user,MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }
        try {
            user.setProfilePicture(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw e; // rethrow the IOException to be handled at a higher level
        }
        usersRepository.save(user);
    }

    public List<Users> getUsers() {
        return new ArrayList<>(usersRepository.findAll());
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

    public Users isValidUser(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
