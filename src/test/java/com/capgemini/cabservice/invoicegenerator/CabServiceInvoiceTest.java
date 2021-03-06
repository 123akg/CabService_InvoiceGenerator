package com.capgemini.cabservice.invoicegenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabServiceInvoiceTest {
	
	 CabServiceInvoice invoiceService;
	    RideRepository rideRepository;

	    @Before
	    public void setUp() throws Exception {
	        invoiceService = new CabServiceInvoice();
	        rideRepository = new RideRepository();
	    }

	    //UseCase1
	    @Test
	    public void givenDistanceAndTime_ShouldReturnCorrectTotalFare() {
	        double fare = invoiceService.calculateFare(2.0, 5, RideCategory.NORMAL);
	        Assert.assertEquals(25, fare, 0.0);
	    }

	    //UseCase2
	    @Test
	    public void givenMinDistanceAndTime_ShouldReturnCorrectTotalFare() {
	        double fare = invoiceService.calculateFare(0.5, 3, RideCategory.NORMAL);
	        Assert.assertEquals(8, fare, 0.0);
	    }

	    //UseCase3
	    @Test
	    public void givenDistanceAndTimeForMultipleRides_ShouldReturnInvoiceSummary() {
	        Ride[] ride = {
	                new Ride(2.0, 5, RideCategory.NORMAL),
	                new Ride(0.1, 1, RideCategory.NORMAL)};

	        InvoiceSummary invoiceSummary = invoiceService.calculateRide(ride);
	        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
	        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	    }

	    //UseCase4
	    @Test
	    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
	        String userId = "plk@ggg.com";
	        Ride[] ride = {
	                new Ride(2.0, 5, RideCategory.NORMAL),
	                new Ride(0.1, 1, RideCategory.NORMAL)};
	        invoiceService.addRides(userId, ride);
	        InvoiceSummary invoiceSummary = invoiceService.getInvoiceSummary(userId);
	        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
	        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	    }

	    //UseCase5
	    @Test
	    public void givenUserIdAndPremiumRides_ShouldReturnInvoiceSummary() {
	        String userId = "akp@ggg.com";
	        Ride[] rides = {new Ride(2.0, 5, RideCategory.PREMIUM),
	                new Ride(0.1, 1, RideCategory.PREMIUM),
	        };
	        invoiceService.addRides(userId, rides);
	        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
	        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
	        Assert.assertEquals(expectedInvoiceSummary, summary);
	    }

	    //UseCase3 premium
	    @Test
	    public void givenDistanceAndTimeForMultipleRidesForPremium_ShouldReturnInvoiceSummary() {
	        Ride[] ride = {
	                new Ride(2.0, 5, RideCategory.PREMIUM),
	                new Ride(0.1, 1, RideCategory.PREMIUM)};

	        InvoiceSummary invoiceSummary = invoiceService.calculateRide(ride);
	        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
	        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
	    }

	    @Test
	    public void givenUserIdAndNormalAndPremiumRides_ShouldReturnInvoiceSummary() {
	        String userId = "akp@ggg.com";
	        Ride[] rides = {new Ride(2.0, 5, RideCategory.NORMAL),
	                new Ride(2.0, 5, RideCategory.PREMIUM),
	        };
	        invoiceService.addRides(userId, rides);
	        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
	        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 65);
	        Assert.assertEquals(expectedInvoiceSummary, summary);
	    }
}
