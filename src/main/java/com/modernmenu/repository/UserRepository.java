package com.modernmenu.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.modernmenu.entity.Owner;

public interface UserRepository extends JpaRepository<Owner, String> {

  Optional<Owner> findByUserName(String userName);

}
