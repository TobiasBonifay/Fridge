package edu.polytech.fridge.models;

public class Recipe {
    private int id;
    private String nom;
    private String ingredients;
    private String preparation;
    private String imageUrl;

    public Recipe() {
    }

    public Recipe(int id, String nom, String ingredients, String preparation) {
        this.id = id;
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparation = preparation;
    }

    public Recipe(int id, String nom, String ingredients, String preparation, String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.imageUrl = imageUrl;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
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
