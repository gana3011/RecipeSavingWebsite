package com.project.recipe.Repository;

import com.project.recipe.Entity.Recipe;
import com.project.recipe.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    Optional<Recipe> findByUser(User user);
}
