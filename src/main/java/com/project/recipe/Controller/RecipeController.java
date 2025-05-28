package com.project.recipe.Controller;

import com.project.recipe.Dto.RecipeDto;
import com.project.recipe.Service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/users/{userid}/recipes")
public class RecipeController {
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@PathVariable("userid") Long userId, @RequestBody RecipeDto recipeDto){
        RecipeDto savedRecipe = recipeService.createRecipeWithTags(recipeDto, userId);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
}
