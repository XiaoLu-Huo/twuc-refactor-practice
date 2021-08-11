package com.twu.refactoring;

public class Direction {
    private final char direction;

    public Direction(char direction) {
        this.direction = direction;
    }

    public Direction turnRight() {
        return getDirection('E', 'W');
    }

    private Direction getDirection(char firstDirection, char secondDirection) {
        switch (direction) {
            case 'N':
                return new Direction(firstDirection);
            case 'S':
                return new Direction(secondDirection);
            case 'E':
                return new Direction('N');
            case 'W':
                return new Direction('S');
            default:
                throw new IllegalArgumentException();
        }
    }

    public Direction turnLeft() {
        return getDirection('W', 'E');
    }

    @Override
    public boolean equals(Object objectDirection) {
        if (this == objectDirection) return true;
        if (objectDirection == null || getClass() != objectDirection.getClass()) return false;

        Direction direction = (Direction) objectDirection;

        return this.direction == direction.direction;
    }

    @Override
    public int hashCode() {
        return direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
