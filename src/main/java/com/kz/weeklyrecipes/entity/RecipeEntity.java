package com.kz.weeklyrecipes.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "recipes")
public class RecipeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(columnDefinition = "text")
  private String steps;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<IngredientEntity> ingredients = new ArrayList<>();

  // getters/setters/ctors omitted for brevity
  public void addIngredient(IngredientEntity i) {
    i.setRecipe(this);
    ingredients.add(i);
  }
  public Long getId() { return id; }
  public String getName() { return name; }
  public String getSteps() { return steps; }
  public List<IngredientEntity> getIngredients() { return ingredients; }
  public void setId(Long id) { this.id = id; }
  public void setName(String name) { this.name = name; }
  public void setSteps(String steps) { this.steps = steps; }
  public void setIngredients(List<IngredientEntity> ingredients) { this.ingredients = ingredients; }
  public RecipeEntity() {}
  public RecipeEntity(String name, String steps) {
    this.name = name;
    this.steps = steps;
  }
}