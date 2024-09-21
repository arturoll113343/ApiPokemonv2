package com.pokemon.pokemonApi.dto;

import java.util.List;

public class PokemonDTO {

    private int id;
    private String name;
    private int height;
    private int weight;
    private List<Ability> abilities;

    // Getters y setters

    public static class Ability {
        private AbilityDetails ability;

        // Getters y setters

        public AbilityDetails getAbility() {
            return ability;
        }

        public void setAbility(AbilityDetails ability) {
            this.ability = ability;
        }

        public static class AbilityDetails {
            private String name;

            // Getters y setters

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    // Getters y setters para los otros campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
