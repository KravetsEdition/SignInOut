package com.example.signInOut.service;

import com.example.signInOut.model.UsersModel;
import com.example.signInOut.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String fullName, String email,
                                   String phone, String status, String password) {
        if (email != null && !usersRepository.findFirstByEmail(email).isPresent()) { // пустой, имеется ли дубликат
            UsersModel usersModel = new UsersModel();
            usersModel.setFullName(fullName);
            usersModel.setEmail(email);
            usersModel.setPhone(phone);
            usersModel.setStatus(status);
            usersModel.setPassword(password);
            return usersRepository.save(usersModel);
        }
        System.out.println("dublicate email");
        return null;
    }

    public UsersModel authenticate(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
