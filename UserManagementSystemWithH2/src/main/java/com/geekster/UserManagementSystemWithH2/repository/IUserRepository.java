package com.geekster.UserManagementSystemWithH2.repository;

import com.geekster.UserManagementSystemWithH2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IUserRepository extends JpaRepository<User,Integer> {
}
