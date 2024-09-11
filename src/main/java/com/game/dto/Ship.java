package com.game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {

    private String position;
    private String targetToEnemy;
    private int owner;
    private String [][] locationShip;
    private int damage;

}
