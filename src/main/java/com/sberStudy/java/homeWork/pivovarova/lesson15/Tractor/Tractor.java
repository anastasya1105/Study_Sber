package com.sberStudy.java.homeWork.pivovarova.lesson15.Tractor;

public class Tractor {
    Position field = new Position(5, 5);
    Orientation orientation = Orientation.NORTH;
    Position position = new Position(0, 0);

    public void move(String command) {
        if ("F".equals(command)) {
            moveForwards();
        }
        else if ("T".equals(command)) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        position = orientation.moveForwards(position);
    }

    public void turnClockwise() {
        orientation.turnClockwise();
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Orientation getOrientation() {
        return orientation;
    }
    private void checkTractorInDitch() {
        if (getPositionX() > field.getX() || getPositionY() > field.getY())
            throw new TractorInDitchException();
    }
}
