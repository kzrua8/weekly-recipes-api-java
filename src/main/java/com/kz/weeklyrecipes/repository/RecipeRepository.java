package com.kz.weeklyrecipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kz.weeklyrecipes.entity.RecipeEntity;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {}