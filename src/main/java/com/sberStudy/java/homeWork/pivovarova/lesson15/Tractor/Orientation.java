package com.sberStudy.java.homeWork.pivovarova.lesson15.Tractor;

public enum Orientation {
    NORTH {
        public Orientation turnClockwise() {
            return Orientation.EAST;
        }

        public Position moveForwards(Position position) {
            return new Position(position.getX(), position.getY() + 1);
        }

    }, WEST {
        public Orientation turnClockwise() {
            return Orientation.NORTH;
        }

        public Position moveForwards(Position position) {
            return new Position(position.getX() - 1, position.getY());
        }

    }, SOUTH {
        public Orientation turnClockwise() {
            return Orientation.WEST;
        }

        public Position moveForwards(Position position) {
            return new Position(position.getX(), position.getY() - 1);
        }

    }, EAST {
        public Orientation turnClockwise() {
            return Orientation.SOUTH;
        }

        public Position moveForwards(Position position) {
            return new Position(position.getX() + 1, position.getY());
        }
    };
    public abstract Position moveForwards(Position position);
    public abstract Orientation turnClockwise();
}
