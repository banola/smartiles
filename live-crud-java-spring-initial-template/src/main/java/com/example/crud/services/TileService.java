package com.example.crud.services;

import org.springframework.stereotype.Service;

@Service
public class TileService {

    public double calc_area(double height, double width){
        double area;
        area = height * width;
        return area;
    }
}
