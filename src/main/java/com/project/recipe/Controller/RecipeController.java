package com.project.recipe.Controller;

import com.project.recipe.Dto.RecipeDto;
import com.project.recipe.Service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/recipes")
public class RecipeController {
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto){
        RecipeDto savedRecipe = recipeService.createRecipeWithTags(recipeDto);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
}
