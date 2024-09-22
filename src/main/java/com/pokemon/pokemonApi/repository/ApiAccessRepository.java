package com.pokemon.pokemonApi.repository;

import com.pokemon.pokemonApi.entity.ApiAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAccessRepository extends JpaRepository<ApiAccess, Long> {
}
