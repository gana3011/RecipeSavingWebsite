package com.project.recipe.Repository;

import com.project.recipe.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
  Optional<Tag> findByName(String name);
}
