package com.bci.user.repository;

import com.bci.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsUserByEmail(String email);

    Optional<User> findByIdAndIsActiveTrue(String id);

    Optional<User> findByEmailAndIsActiveTrue(String email);

    List<User> findAllByIsActiveTrue();
}
