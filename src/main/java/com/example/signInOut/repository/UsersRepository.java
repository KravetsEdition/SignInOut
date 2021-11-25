package com.example.signInOut.repository;

import com.example.signInOut.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel,Integer> {

    Optional<UsersModel> findByEmailAndPassword(String email, String password);

    Optional<UsersModel> findFirstByEmail(String email);

}
