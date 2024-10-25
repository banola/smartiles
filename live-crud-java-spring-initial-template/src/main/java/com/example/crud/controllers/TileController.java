package com.example.crud.controllers;

import com.example.crud.request.RequestTile;
import com.example.crud.model.tile.Tile;
import com.example.crud.repository.TileRepository;
import com.example.crud.services.TileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tile")
public class TileController {

    @Autowired
    private TileRepository repository;
    private TileService tileservice;

    @Autowired
    public TileController(TileRepository repository, TileService tileservice) {
        this.repository = repository;
        this.tileservice = tileservice;
    }

    @GetMapping
    public ResponseEntity getAllTiles(){
        var allTiles = repository.findAll();
        return ResponseEntity.ok(allTiles);
    }

    @PostMapping
    public ResponseEntity registerTile(@RequestBody RequestTile data){
        Tile newTile = new Tile(data);
        newTile.setArea(tileservice.calc_area(data.height(), data.width()));
        repository.save(newTile);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateTile(@RequestBody RequestTile data){
        Optional<Tile> optionalTile = repository.findById(data.id());
        if(optionalTile.isPresent()){
            Tile tile = optionalTile.get();
            tile.setHeight(data.height());
            tile.setWidth(data.width());
            return ResponseEntity.ok(tile);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTile(@PathVariable String id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
