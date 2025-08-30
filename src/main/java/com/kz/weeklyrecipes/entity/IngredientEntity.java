package com.kz.weeklyrecipes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "ingredients")
public class IngredientEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private double quantity;
  private String unit;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipe_id")
  private RecipeEntity recipe;

  // getters/setters/ctors
  public Long getId() { return id; }
  public String getName() { return name; }
  public double getQuantity() { return quantity; }
  public String getUnit() { return unit; }
  public RecipeEntity getRecipe() { return recipe; }
  public void setId(Long id) { this.id = id; }
  public void setName(String name) { this.name = name; }
  public void setQuantity(double quantity) { this.quantity = quantity; }
  public void setUnit(String unit) { this.unit = unit; }
  public void setRecipe(RecipeEntity recipe) { this.recipe = recipe; }
  public IngredientEntity() {}
  public IngredientEntity(String name, double quantity, String unit) {          
    this.name = name;
    this.quantity = quantity;
    this.unit = unit;
  }
}