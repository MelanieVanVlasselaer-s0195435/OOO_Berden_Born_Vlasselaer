package model;

public class Category {

    private String name;
    private String description;
    private String hoofdcategorie;

    public Category (String name, String description){
        this.setName(name);
        this.setDescription(description);
        this.hoofdcategorie = null;
    }

    public Category (String name, String description, String hoofdcategorie){
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

    public void setHoofdcategorie(String hoofdcategorie) {
        this.hoofdcategorie = hoofdcategorie;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHoofdcategorie() {
        return hoofdcategorie;
    }

    @Override
    public String toString() {
        return name + " " + description + " " + hoofdcategorie;
    }
}
