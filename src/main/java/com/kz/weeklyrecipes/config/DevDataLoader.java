package com.kz.weeklyrecipes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kz.weeklyrecipes.entity.IngredientEntity;
import com.kz.weeklyrecipes.entity.RecipeEntity;
import com.kz.weeklyrecipes.repository.RecipeRepository;

@Component
@Profile("dev")
public class DevDataLoader implements CommandLineRunner {
  private final RecipeRepository repo;
  public DevDataLoader(RecipeRepository repo) { this.repo = repo; }

  @Override public void run(String... args) {
    if (repo.count() > 0) return;

    var curry = new RecipeEntity();
    curry.setName("Chicken Curry"); curry.setSteps("Cut, sauté, simmer.");
    curry.addIngredient(new IngredientEntity("chicken", 500, "GRAM"));
    curry.addIngredient(new IngredientEntity("onion", 1, "PIECE"));
    repo.save(curry);
  }
}