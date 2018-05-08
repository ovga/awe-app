package com.awe.backend.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.awe.backend.persistence.domain.backend.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	Long countByName(String name);

	Optional<Tag> findByName(String name);
}
