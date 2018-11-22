package model;

public class Category {

    private String name;
    private String description;

    public Category (String name, String description){
        this.setName(name);
        this.setDescription(description);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setDescription(String description){
        this.description = description;
    }

}
