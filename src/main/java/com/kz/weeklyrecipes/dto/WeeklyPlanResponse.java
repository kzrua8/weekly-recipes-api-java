package com.kz.weeklyrecipes.dto;
import java.util.List;

public record WeeklyPlanResponse(String weekStart, List<Long> recipeIds, List<IngredientDto> combinedIngredients) {}