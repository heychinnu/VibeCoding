package com.taste.repository;

import com.taste.model.User;
import com.taste.model.DietaryPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.username LIKE %:query% OR u.firstName LIKE %:query% OR u.lastName LIKE %:query%")
    List<User> searchUsers(@Param("query") String query);
    
    @Query("SELECT u FROM User u WHERE :preference MEMBER OF u.dietaryPreferences")
    List<User> findByDietaryPreference(@Param("preference") DietaryPreference preference);
    
    @Query("SELECT u FROM User u WHERE u.dietaryPreferences IN :preferences")
    List<User> findByDietaryPreferences(@Param("preferences") Set<DietaryPreference> preferences);
    
    @Query("SELECT u FROM User u JOIN u.followers f WHERE f.id = :userId")
    List<User> findFollowersByUserId(@Param("userId") Long userId);
    
    @Query("SELECT u FROM User u JOIN u.following f WHERE f.id = :userId")
    List<User> findFollowingByUserId(@Param("userId") Long userId);
}