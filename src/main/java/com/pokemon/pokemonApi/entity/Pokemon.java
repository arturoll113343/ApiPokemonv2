package com.pokemon.pokemonApi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int height;
    private int weight;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private List<ApiAccess> apiAccesses;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<ApiAccess> getApiAccesses() {
        return apiAccesses;
    }

    public void setApiAccesses(List<ApiAccess> apiAccesses) {
        this.apiAccesses = apiAccesses;
    }
}
