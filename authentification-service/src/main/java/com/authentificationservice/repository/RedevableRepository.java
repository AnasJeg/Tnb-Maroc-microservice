package com.authentificationservice.repository;

import com.authentificationservice.entities.Redevable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedevableRepository extends JpaRepository<Redevable, Long> {

    Optional<Redevable> findByCin(String cin);
}
