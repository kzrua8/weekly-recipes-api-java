package com.kz.weeklyrecipes.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kz.weeklyrecipes.dto.IngredientDto;
import com.kz.weeklyrecipes.dto.RecipeDto;
import com.kz.weeklyrecipes.dto.WeeklyPlanResponse;

@RestController
@RequestMapping("/api")
public class RecipeController {

  private final List<RecipeDto> recipes = new ArrayList<>();
  private final AtomicLong seq = new AtomicLong(1);

  public RecipeController() {
    recipes.add(new RecipeDto(
        seq.getAndIncrement(), "Chicken Curry", "Cut, sauté, simmer.",
        List.of(new IngredientDto("chicken", 500, "GRAM"), new IngredientDto("onion", 1, "PIECE"))
    ));
    recipes.add(new RecipeDto(
        seq.getAndIncrement(), "Tomato Pasta", "Boil, sauce, mix.",
        List.of(new IngredientDto("pasta", 200, "GRAM"), new IngredientDto("tomato", 2, "PIECE"))
    ));
  }

  @GetMapping("/recipes")
  public List<RecipeDto> list() { return recipes; }

  @PostMapping("/recipes")
  public RecipeDto create(@RequestBody RecipeDto req) {
    RecipeDto r = new RecipeDto(seq.getAndIncrement(), req.name(), req.steps(), req.ingredients());
    recipes.add(r);
    return r;
  }

  @GetMapping("/weeks/{date}/plan")
  public WeeklyPlanResponse plan(@PathVariable String date) {
    var picked = recipes.stream().limit(7).map(RecipeDto::id).toList();

    Map<String, Double> totals = new HashMap<>();
    Map<String, String> units = new HashMap<>();

    recipes.stream().limit(7).forEach(r -> r.ingredients().forEach(i -> {
      String key = i.name().toLowerCase() + "|" + i.unit();
      totals.put(key, totals.getOrDefault(key, 0.0) + i.quantity());
      units.put(key, i.unit());
    }));

    List<IngredientDto> combined = totals.entrySet().stream()
        .map(e -> {
          String[] parts = e.getKey().split("\\|");
          return new IngredientDto(parts[0], e.getValue(), units.get(e.getKey()));
        })
        .collect(Collectors.toList());

    return new WeeklyPlanResponse(date, picked, combined);
  }
}