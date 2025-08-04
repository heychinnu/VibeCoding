package com.taste.repository;

import com.taste.model.Restaurant;
import com.taste.model.User;
import com.taste.model.DietaryPreference;
import com.taste.model.PrivacyLevel;
import com.taste.model.PriceRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    List<Restaurant> findByAuthor(User author);
    
    List<Restaurant> findByAuthorAndPrivacyLevel(User author, PrivacyLevel privacyLevel);
    
    Optional<Restaurant> findByShareToken(String shareToken);
    
    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %:query% OR r.description LIKE %:query% OR r.address LIKE %:query%")
    List<Restaurant> searchRestaurants(@Param("query") String query);
    
    @Query("SELECT r FROM Restaurant r WHERE r.privacyLevel = 'PUBLIC' AND (r.name LIKE %:query% OR r.description LIKE %:query% OR r.address LIKE %:query%)")
    List<Restaurant> searchPublicRestaurants(@Param("query") String query);
    
    @Query("SELECT r FROM Restaurant r WHERE :cuisine MEMBER OF r.cuisines")
    List<Restaurant> findByCuisine(@Param("cuisine") String cuisine);
    
    @Query("SELECT r FROM Restaurant r WHERE :tag MEMBER OF r.tags")
    List<Restaurant> findByTag(@Param("tag") String tag);
    
    List<Restaurant> findByPriceRange(PriceRange priceRange);
    
    @Query("SELECT r FROM Restaurant r WHERE :option MEMBER OF r.dietaryOptions")
    List<Restaurant> findByDietaryOption(@Param("option") DietaryPreference option);
    
    @Query("SELECT r FROM Restaurant r WHERE r.dietaryOptions IS EMPTY OR r.dietaryOptions MEMBER OF :options")
    List<Restaurant> findByDietaryOptions(@Param("options") Set<DietaryPreference> options);
    
    @Query("SELECT r FROM Restaurant r WHERE r.address LIKE %:location%")
    List<Restaurant> findByLocation(@Param("location") String location);
    
    @Query("SELECT r FROM Restaurant r WHERE r.privacyLevel = 'PUBLIC' ORDER BY r.createdAt DESC")
    List<Restaurant> findPublicRestaurantsOrderByCreatedDesc();
    
    @Query("SELECT r FROM Restaurant r WHERE r.author IN :followedUsers AND r.privacyLevel IN ('PUBLIC', 'FRIENDS') ORDER BY r.createdAt DESC")
    List<Restaurant> findRestaurantsByFollowedUsers(@Param("followedUsers") List<User> followedUsers);
    
    // Find potential duplicates by name and address similarity
    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %:name% AND r.address LIKE %:address% AND r.id != :excludeId")
    List<Restaurant> findPotentialDuplicates(@Param("name") String name, @Param("address") String address, @Param("excludeId") Long excludeId);
}