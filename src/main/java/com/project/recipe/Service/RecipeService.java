package com.project.recipe.Service;

import com.project.recipe.Dto.RecipeDto;
import com.project.recipe.Entity.Recipe;
import com.project.recipe.Entity.Tag;
import com.project.recipe.Mapper.RecipeMapper;
import com.project.recipe.Repository.RecipeRepository;
import com.project.recipe.Repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {
    private RecipeRepository recipeRepository;
    private TagRepository tagRepository;
    @Transactional
    public RecipeDto createRecipeWithTags(RecipeDto recipeDto){
        Set<Tag> tags = recipeDto.getTags().stream().map(name->tagRepository.findByName(name).orElseGet(()->{
            Tag newTag = new Tag();
            newTag.setName(name);
            return tagRepository.save(newTag);
                }
        )).collect(Collectors.toSet());
        Recipe recipe = RecipeMapper.convertToEntity(recipeDto,tags);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return RecipeMapper.convertToDto(savedRecipe);
    }
}
