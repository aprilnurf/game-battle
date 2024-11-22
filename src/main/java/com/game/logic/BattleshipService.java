package com.game.logic;

import com.game.dto.Ship;

public class BattleshipService {

    public String initPosition(Ship ship) {
        //1,1:2,0:2,3:3,4:4,3
        //0,1:2,3:3,0:3,4:4,1
        String[] positions = ship.getPosition().split(":");
        StringBuffer sb = new StringBuffer();
        String[][] location = new String[5][5];
        for (String position : positions) {
            String[] xy = position.split(",");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            location[x][y] = "B";
            if (!sb.isEmpty()) {
                sb.append(":");
            }
            sb.append(x + "," + y);
        }
        ship.setLocationShip(location);
        return sb.toString();
    }

    public int shot(Ship ship, Ship targetShip) {
        String[] targets = ship.getTargetToEnemy().split(":");
        int count = 0;

        for (String target : targets) {
            String[] xy = target.split(",");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            if (targetShip.getLocationShip().length > x && targetShip.getLocationShip()[0].length > y) {
                if (targetShip.getLocationShip()[x][y] == "B") {
                    targetShip.getLocationShip()[x][y] = "X";
                    count++;
                } else {
                    targetShip.getLocationShip()[x][y] = "O";
                }
            }
        }
        ship.setDamage(count);
        return count;
    }

    public String winner(Ship ship, Ship targetShip) {
        if (ship.getDamage() == targetShip.getDamage()) {
            return "Draw";
        }
        return ship.getDamage() > targetShip.getDamage() ? "Player "+targetShip.getOwner() : "Player "+ship.getOwner();
    }
}
