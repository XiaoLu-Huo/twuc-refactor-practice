package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

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
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

}
