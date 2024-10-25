package com.example.crud.repository;

import com.example.crud.model.tile.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TileRepository extends JpaRepository<Tile, String> {

}
