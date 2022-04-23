package edu.polytech.fridge.models;

public class Recipe {
    int id;
    String nom;
    String ingredients;
    String preparation;

    public Recipe() {
    }

    public Recipe(int id, String nom, String ingredients, String preparation) {
        this.id = id;
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparation = preparation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", preparation='" + preparation + '\'' +
                '}';
    }
}
