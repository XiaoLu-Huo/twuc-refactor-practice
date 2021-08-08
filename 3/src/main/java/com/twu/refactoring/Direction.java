package com.twu.refactoring;

public class Direction {
    private final char direction;

    public Direction(char direction) {
        this.direction = direction;
    }

    public Direction turnRight() {
        return getDirection('E', 'W', 'N','S');
    }

    private Direction getDirection(char firstDirection, char secondDirection,char thirdDirection, char fourthDirection) {
        switch (direction) {
            case 'N':
                return new Direction(firstDirection);
            case 'S':
                return new Direction(secondDirection);
            case 'E':
                return new Direction(thirdDirection);
            case 'W':
                return new Direction(fourthDirection);
            default:
                throw new IllegalArgumentException();
        }
    }

    public Direction turnLeft() {
        return getDirection('W', 'E','N','S');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        return direction == direction1.direction;
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
