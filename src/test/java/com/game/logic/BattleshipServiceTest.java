package com.game.logic;

import com.game.dto.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class BattleshipServiceTest {

    @InjectMocks
    private BattleshipService battleshipService;

    @Test
    void initTest() {
        //init the position of each ship on player 1 & 2
        Ship ship = new Ship();
        ship.setPosition("1,1:2,0:2,3:3,4:4,3");
        battleshipService.initPosition(ship);
        Assertions.assertNotNull(ship.getLocationShip());
    }

    @Test
    void initTestCaseCountAlive() {
        //init the position of each ship on player 1 & 2
        Ship ship = new Ship();
        ship.setPosition("2,0:2,3:3,4:4,3:1,1"); // -> 1,1:
        String positionAppend = battleshipService.initPosition(ship);
        Assertions.assertNotNull(ship.getLocationShip());

        int count = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ship.getLocationShip().length; i++) {
            for (int j = 0; j < ship.getLocationShip()[i].length; j++) {
                if (ship.getLocationShip()[i][j] == "B") {
                    if (!sb.isEmpty()) {
                        sb.append(":");
                    }
                    sb.append(i + "," + j);
                    count++;
                }
            }
        }

        Assertions.assertEquals(positionAppend, ship.getPosition());
        Assertions.assertEquals(5, count);
    }

    @Test
    void shot() {
        //expected : count the damage ship
        Ship ship = new Ship();
        ship.setPosition("1,1:2,0:2,3:3,4:4,3");
        ship.setTargetToEnemy("0,1:4,3:2,3:3,1:4,1:10,10");
        String[][] location1 = new String[5][5];
        location1[1][1] = "B";
        location1[2][0] = "B";
        location1[2][3] = "B";
        location1[3][4] = "B";
        location1[4][3] = "B";
        ship.setLocationShip(location1);

        Ship ship2 = new Ship();
        ship2.setPosition("0,1:2,3:3,0:3,4:4,1");
        ship2.setTargetToEnemy("0,1:0,0:1,1:2,3:4,3:10,10");
        String[][] location = new String[5][5];
        location[0][1] = "B";
        location[2][3] = "B";
        location[3][0] = "B";
        location[3][4] = "B";
        location[4][1] = "B";
        ship2.setLocationShip(location);

        int damage = battleshipService.shot(ship, ship2);
        int damage2 = battleshipService.shot(ship2, ship);

        Assertions.assertEquals(3, damage);
        Assertions.assertEquals(3, damage2);
    }

    @Test
    void getWinnerTest() {
        Ship ship = new Ship();
        ship.setDamage(1);
        ship.setOwner(1);

        Ship ship2 = new Ship();
        ship2.setDamage(2);
        ship2.setOwner(2);
        String winner  = battleshipService.winner(ship, ship2);
        Assertions.assertEquals("Player 1", winner);
    }

    @Test
    void getWinnerDrawTest() {
        Ship ship = new Ship();
        ship.setDamage(2);
        ship.setOwner(1);

        Ship ship2 = new Ship();
        ship2.setDamage(2);
        ship2.setOwner(2);
        String winner  = battleshipService.winner(ship, ship2);
        Assertions.assertEquals("Draw", winner);
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

}
