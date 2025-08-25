package com.kz.weeklyrecipes.api.dto;
import java.util.List;

public record WeeklyPlanResponse(String weekStart, List<Long> recipeIds, List<IngredientDto> combinedIngredients) {}