package com.project.recipe.Controller;

import com.project.recipe.Dto.RecipeDto;
import com.project.recipe.Dto.ResponseDto;
import com.project.recipe.Entity.Recipe;
import com.project.recipe.Service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getRecipe(@PathVariable("userid") Long userId){
        List<RecipeDto> recipes = recipeService.getRecipe(userId);
        return new ResponseEntity<>(recipes, HttpStatus.CREATED);
    }

    @DeleteMapping("/{recipeid}")
    public ResponseEntity<ResponseDto> deleteRecipe(@PathVariable("recipeid") Long recipeId){
        ResponseDto response = recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
