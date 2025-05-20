package com.project.recipe.Mapper;


import com.project.recipe.Dto.RecipeDto;
import com.project.recipe.Entity.Recipe;
import com.project.recipe.Entity.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class RecipeMapper {
    public static RecipeDto convertToDto(Recipe recipe){
        Set<String> tagNames = recipe.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getUrl(),
                tagNames
        );
    }

    public static Recipe convertToEntity(RecipeDto recipeDto, Set<Tag> tags){
        Recipe recipe = new Recipe();
        recipe.setId(recipeDto.getId());
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setUrl(recipeDto.getUrl());
        recipe.setTags(tags);
        return recipe;
    }
}
