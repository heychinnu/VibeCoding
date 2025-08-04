package com.taste.repository;

import com.taste.model.Recipe;
import com.taste.model.User;
import com.taste.model.DietaryPreference;
import com.taste.model.PrivacyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    List<Recipe> findByAuthor(User author);
    
    List<Recipe> findByAuthorAndPrivacyLevel(User author, PrivacyLevel privacyLevel);
    
    Optional<Recipe> findByShareToken(String shareToken);
    
    @Query("SELECT r FROM Recipe r WHERE r.title LIKE %:query% OR r.description LIKE %:query%")
    List<Recipe> searchRecipes(@Param("query") String query);
    
    @Query("SELECT r FROM Recipe r WHERE r.privacyLevel = 'PUBLIC' AND (r.title LIKE %:query% OR r.description LIKE %:query%)")
    List<Recipe> searchPublicRecipes(@Param("query") String query);
    
    @Query("SELECT r FROM Recipe r WHERE :category MEMBER OF r.categories")
    List<Recipe> findByCategory(@Param("category") String category);
    
    @Query("SELECT r FROM Recipe r WHERE :tag MEMBER OF r.tags")
    List<Recipe> findByTag(@Param("tag") String tag);
    
    @Query("SELECT r FROM Recipe r WHERE :preference MEMBER OF r.dietaryPreferences")
    List<Recipe> findByDietaryPreference(@Param("preference") DietaryPreference preference);
    
    @Query("SELECT r FROM Recipe r WHERE r.dietaryPreferences IS EMPTY OR r.dietaryPreferences MEMBER OF :preferences")
    List<Recipe> findByDietaryPreferences(@Param("preferences") Set<DietaryPreference> preferences);
    
    @Query("SELECT r FROM Recipe r WHERE r.allergens IS EMPTY OR SIZE(r.allergens) = 0")
    List<Recipe> findRecipesWithoutAllergens();
    
    @Query("SELECT r FROM Recipe r WHERE r.allergens IS EMPTY OR NOT EXISTS (SELECT a FROM r.allergens a WHERE a IN :allergens)")
    List<Recipe> findRecipesSafeForAllergies(@Param("allergens") Set<String> allergens);
    
    @Query("SELECT r FROM Recipe r WHERE r.privacyLevel = 'PUBLIC' ORDER BY r.createdAt DESC")
    List<Recipe> findPublicRecipesOrderByCreatedDesc();
    
    @Query("SELECT r FROM Recipe r WHERE r.author IN :followedUsers AND r.privacyLevel IN ('PUBLIC', 'FRIENDS') ORDER BY r.createdAt DESC")
    List<Recipe> findRecipesByFollowedUsers(@Param("followedUsers") List<User> followedUsers);
}