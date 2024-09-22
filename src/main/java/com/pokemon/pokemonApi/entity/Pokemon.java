package com.pokemon.pokemonApi.entity;

import com.pokemon.pokemonApi.dto.EvolutionChainDTO;
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

    // Nuevo campo para la cadena de evolución
    @Transient // Esto indica que no se debe mapear a la base de datos
    private EvolutionChainDTO evolutionChain;

    // Getters y setters

    public int getId() {
        return Math.toIntExact(id);
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

    public EvolutionChainDTO getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(EvolutionChainDTO evolutionChain) {
        this.evolutionChain = evolutionChain;
    }
}
