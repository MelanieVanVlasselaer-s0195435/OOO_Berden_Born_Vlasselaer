package model;

public class Category {

    private String name;
    private String description;
    private Category hoofdcategorie;

    public Category (String name, String description){
        this.setName(name);
        this.setDescription(description);
        this.hoofdcategorie = null;
    }

    public Category (String name, String description, Category hoofdcategorie){
        this.setName(name);
        this.setDescription(description);
        this.hoofdcategorie = hoofdcategorie;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setDescription(String description){
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getHoofdcategorie() {
        return hoofdcategorie;
    }

    public void setHoofdcategorie(Category hoofdcategorie) {
        this.hoofdcategorie = hoofdcategorie;
    }
}
