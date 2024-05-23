package com.example.pokedexls.Model;

public class Pokemon {

    private String name;
    private String frontImage;
    private String backImage;
    private String pokeballImage;
    private String pokeball;
    private String[] typeImage;
    private String[] type;
    private String description;
    private String hability;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;


    public Pokemon() {

    }

    public Pokemon(String name, String frontImage, String backImage, String[] typeImage, String[] type, String description, String hability, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed, String pokeballImage, String pokeball) {
        this.name = name;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.typeImage = typeImage;
        this.type = type;
        this.description = description;
        this.hability = hability;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.pokeballImage = pokeballImage;
        this.pokeball = pokeball;
    }

    public String getName() {
        return name;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public String[] getTypeImage() {
        return typeImage;
    }

    public String[] getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getHability() {
        return hability;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getPokeballImage() {
        return pokeballImage;
    }

    public String getPokeball() {
        return pokeball;
    }
}
