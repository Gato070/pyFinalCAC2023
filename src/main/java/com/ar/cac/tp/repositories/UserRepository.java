package com.ar.cac.tp.repositories;

import com.ar.cac.tp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Long>{

    public boolean findByEmail(String email);
}
