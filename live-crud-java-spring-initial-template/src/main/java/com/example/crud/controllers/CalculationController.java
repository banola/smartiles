package com.example.crud.controllers;

import com.example.crud.request.RequestCalculation;
import com.example.crud.response.ResponseCalculation;
import com.example.crud.repository.OperationRepository;
import com.example.crud.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Esta classe é responsável pelo funcionamento da conta para definir a quantidade de pisos.
// A função RestController define esta classe como um controller para o Spring, a RequestMapping define o caminho url para acessar esta controller.
@RestController
@RequestMapping("/calculation")
public class CalculationController {
    // Atravéz da função @Autowired do Spring injetamos valores referentes a outras classes do nosso programa nesta Classe
    // no caso estamos injetando valores da classe CalculationService.
    @Autowired
    private final CalculationService calcservice;

    @Autowired
    public CalculationController(CalculationService calcservice) {
        this.calcservice = calcservice;
    }
    // A função @PostMapping é responsável pela execução do metodo atribuido à ela quando houver uma requisição do tipo POST.
    // O metodo tiles_calc realiza a conta dos pisos utilizando a lógica presente na classe CalculationService. Este metodo não
    // realiza a persistencia dos dados, mas sim, apenas o cálculo da quantidade de pisos necessários.
    @PostMapping
    public ResponseEntity<ResponseCalculation> tiles_calc(@RequestBody RequestCalculation request) {

        double area_room = calcservice.area_room(request.room_height(), request.room_width());
        double area_tile = calcservice.area_tile(request.tile_height(), request.tile_width());
        double total_tiles = calcservice.total_tiles(area_room, area_tile);
        double final_tiles = calcservice.final_tles(total_tiles, request.loss_perc());
        // A classe ResponseClaculation se mostra necessária pois não estamos retornando apenas 1 valor, mas sim 2 valores no fim deste metodo.
        ResponseCalculation rq = new ResponseCalculation(total_tiles, final_tiles);
        return ResponseEntity.ok().body(rq);
    }
}
