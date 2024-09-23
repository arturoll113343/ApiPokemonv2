// src/components/Pokemon.js
import React, { useState } from 'react';
import axios from 'axios';
import './Pokemon.css'; // Importar el archivo CSS

const Pokemon = () => {
    const [pokemonId, setPokemonId] = useState('');
    const [pokemonData, setPokemonData] = useState(null);
    const [error, setError] = useState('');

    const fetchPokemon = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/pokemon/${pokemonId}`);
            setPokemonData(response.data);
            setError('');
        } catch (err) {
            setError('Pokémon no encontrado');
            setPokemonData(null);
        }
    };

    return (
        <div className="pokemon-container">
            <h1>Buscar Pokémon</h1>
            <input
                type="number"
                className="pokemon-input"
                placeholder="Ingrese ID del Pokémon"
                value={pokemonId}
                onChange={(e) => setPokemonId(e.target.value)}
            />
            <button className="pokemon-button" onClick={fetchPokemon}>Buscar</button>

            {error && <p className="pokemon-error">{error}</p>}
            {pokemonData && (
                <div className="pokemon-data">
                    <h2 className="pokemon-name">{pokemonData.name}</h2>
                    <p className="pokemon-info">Altura: {pokemonData.height}</p>
                    <p className="pokemon-info">Peso: {pokemonData.weight}</p>
                    <h3>Cadena de Evolución</h3>
                    {/* Pendiente por agregar */}
                </div>
            )}
        </div>
    );
};

export default Pokemon;
