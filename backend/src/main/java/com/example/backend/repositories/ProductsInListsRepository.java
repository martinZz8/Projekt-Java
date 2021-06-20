package com.example.backend.repositories;

import com.example.backend.model.ProductsInLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsInListsRepository extends JpaRepository<ProductsInLists, Long> {

}
