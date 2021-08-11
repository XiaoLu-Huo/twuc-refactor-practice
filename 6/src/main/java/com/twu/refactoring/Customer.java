package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

    public static final double CHILDREN_POINT = 1.5;
    public static final int CHILDREN_DAYS = 3;
    public static final int REGULAR_DAYS = 2;
    public static final int NEW_RELEASE_POINT = 3;
    private static final double REGULAR_POINT = 1.5;
    public static final double CHILDREN_POINT_INITIAL = 1.5;
    public static final int REGULAR_POINT_INITIAL = 2;
    private final String name;
    private final ArrayList<Rental> rentalList = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentalList.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        while (rentals.hasNext()) {
            double thisAmount = 0;
            Rental each = rentals.next();

            thisAmount = getAmount(thisAmount, each);

            frequentRenterPoints = getRenterPoints(frequentRenterPoints + 1, each);

            result.append("\t")
                    .append(each.getMovie().getTitle())
                    .append("\t")
                    .append(thisAmount)
                    .append("\n");
            totalAmount += thisAmount;

        }
        return result.append("Amount owed is ")
                .append(totalAmount)
                .append("\n").append("You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points")
                .toString();
    }

    private int getRenterPoints(int frequentRenterPoints, Rental each) {
        return (each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1 ?
                frequentRenterPoints + 1 : frequentRenterPoints;
    }

    private double getAmount(double thisAmount, Rental each) {
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += REGULAR_POINT_INITIAL;
                if (each.getDaysRented() > REGULAR_DAYS) {
                    thisAmount += (each.getDaysRented() - REGULAR_DAYS) * REGULAR_POINT;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * NEW_RELEASE_POINT;
                break;
            case Movie.CHILDREN:
                thisAmount += CHILDREN_POINT_INITIAL;
                if (each.getDaysRented() > CHILDREN_DAYS) {
                    thisAmount += (each.getDaysRented() - CHILDREN_DAYS) * CHILDREN_POINT;
                }
                break;
        }
        return thisAmount;
    }

}
