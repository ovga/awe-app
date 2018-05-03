package com.awe.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.awe.backend.persistence.domain.backend.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Long countByName(String name);
}
