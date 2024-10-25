package com.example.crud.controllers;

import com.example.crud.request.RequestCalculation;
import com.example.crud.response.ResponseCalculation;
import com.example.crud.repository.OperationRepository;
import com.example.crud.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculation")
public class CalculationController {

    @Autowired
    private final CalculationService calcservice;

    @Autowired
    public CalculationController(CalculationService calcservice) {
        this.calcservice = calcservice;
    }

    @PostMapping
    public ResponseEntity<ResponseCalculation> tiles_calc(@RequestBody RequestCalculation request) {
        double room_height = request.room_height();
        double room_width = request.room_width();
        double tile_height = request.tile_height();
        double tile_width = request.tile_width();
        double loss_perc = request.loss_perc();

        double area_room = calcservice.area_room(room_height, room_width);
        double area_tile = calcservice.area_tile(tile_height, tile_width);
        double total_tiles = calcservice.total_tiles(area_room, area_tile);
        double final_tiles = calcservice.final_tles(total_tiles, loss_perc);

        ResponseCalculation rq = new ResponseCalculation(total_tiles, final_tiles);
        return ResponseEntity.ok().body(rq);
    }
}
