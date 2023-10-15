package com.ibagroup.common.dao.postgres.repository;

import com.ibagroup.common.dao.postgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "postgresRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
