package com.pokemon.pokemonApi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ApiAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;

    private LocalDateTime accessTime;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    // Getters y setters

    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}