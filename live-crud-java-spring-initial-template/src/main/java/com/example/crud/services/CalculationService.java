package com.example.crud.services;

import org.springframework.stereotype.Service;
// A notação @Service indica ao Spring que esta classe contém regras de negócio
// que serão utilizadas em uma classe Controller.
// No caso esta classe serve para definir as regras de negócio envolvidas durante o cálculo dos pisos.
@Service
public class CalculationService {

    public double area_room(double length, double width){
        double area_r = length*width;
        return area_r;
    }

    public double area_tile(double length, double width){
        double area_t = length*width;
        return area_t;
    }

    public double total_tiles(double area_r, double area_t){
        double total_tiles = Math.ceil(area_r/area_t);
        return total_tiles;
    }

    public double final_tles(double total_tiles, double loss_perc){
        double final_tles = total_tiles + (total_tiles * loss_perc);
        return Math.ceil(final_tles);
    }
}
