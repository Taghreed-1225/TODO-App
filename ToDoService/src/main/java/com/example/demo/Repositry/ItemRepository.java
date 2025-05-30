package com.example.demo.Repositry;

import com.example.demo.entity.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items,Integer> {
   // Page<Items> findAll(Pageable pageable);
   Optional<Items> findByTitle(String title);




}
