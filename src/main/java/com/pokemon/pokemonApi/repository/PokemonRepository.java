package com.pokemon.pokemonApi.repository;

import com.pokemon.pokemonApi.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
