package com.example.pokedexls.Model;

public class Pokemon {
    private String id;
    private String name;
    private String frontImage;
    private String backImage;
    private String[] types;
    private int hp;

    public Pokemon(String id, String name, String frontImage, String backImage, String[] types, int hp) {
        this.id = id;
        this.name = name;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.types = types;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
