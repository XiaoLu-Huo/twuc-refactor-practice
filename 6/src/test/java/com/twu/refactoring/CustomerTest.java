package com.twu.refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CustomerTest {


	private static final String GOLD_PATH = "data/";

    private final Customer disable = new Customer("Disable Piranha");

    private final Movie python = new Movie("Monty Python and the Holy Grail", Movie.REGULAR);
	private final Movie ran = new Movie("Ran", Movie.REGULAR);
	private final Movie la = new Movie("LA Confidential", Movie.NEW_RELEASE);
	private final Movie trek = new Movie("Star Trek 13.2", Movie.NEW_RELEASE);
	private final Movie wallace = new Movie("Wallace and Grommet", Movie.CHILDREN);

    @BeforeEach
    public void setUpData(){
       disable.addRental(new Rental (python, 3));
       disable.addRental(new Rental (ran, 1));
       disable.addRental(new Rental (la, 2));
       disable.addRental(new Rental (trek, 1));
       disable.addRental(new Rental (wallace, 6));
   }

    @Test
    public void shouldOutputEmptyStatement() throws Exception {
        Customer customer = new Customer("Golden Shark");
        verifyOutput(customer.statement(), "outputEmpty");
    }

    @Test
    public void shouldOutputCustomerStatement() throws Exception {
        verifyOutput(disable.statement(), "output1");
    }

    @Test
    public void shouldOutputChangedStatement() throws Exception {
        la.setPriceCode(Movie.REGULAR);
        verifyOutput(disable.statement(), "outputChange");
    }
    	
    protected void verifyOutput(String actualValue, String fileName) throws IOException{
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(GOLD_PATH + fileName)).getPath();
        BufferedReader file = new BufferedReader (new FileReader (filePath));
        BufferedReader actualStream = new BufferedReader (new StringReader (actualValue));
        String thisFileLine;
        while  ((thisFileLine = file.readLine()) != null) {
            assertThat("in file: " + fileName, actualStream.readLine(), equalTo(thisFileLine));
        }
    }

}
