package com.kz.weeklyrecipes.dto;
import java.util.List;

public record RecipeDto(Long id, String name, String steps, List<IngredientDto> ingredients) {}