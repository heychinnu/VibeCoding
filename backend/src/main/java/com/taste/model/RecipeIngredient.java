package com.taste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Positive
    @Column(precision = 10, scale = 3)
    private BigDecimal quantity;

    private String unit; // cups, tablespoons, grams, etc.

    private String notes; // "finely chopped", "room temperature", etc.

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    // Constructors
    public RecipeIngredient() {}

    public RecipeIngredient(String name, BigDecimal quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }

    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    // Helper methods
    public String getDisplayText() {
        StringBuilder sb = new StringBuilder();
        if (quantity != null) {
            sb.append(quantity);
            if (unit != null && !unit.trim().isEmpty()) {
                sb.append(" ").append(unit);
            }
            sb.append(" ");
        }
        sb.append(name);
        if (notes != null && !notes.trim().isEmpty()) {
            sb.append(" (").append(notes).append(")");
        }
        return sb.toString();
    }
}