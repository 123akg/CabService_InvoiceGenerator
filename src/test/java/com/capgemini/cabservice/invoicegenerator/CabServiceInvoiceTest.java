package com.capgemini.cabservice.invoicegenerator;

import org.junit.Assert;
import org.junit.Test;


public class CabServiceInvoiceTest {
	
	@Test
	public void givenDistanceAndTime_ShouldReturnCorrectTotalFare() {
		CabServiceInvoice invoiceService = new CabServiceInvoice();
		double fare = invoiceService.calculateFare(2.0, 5);
		Assert.assertEquals(25, fare, 0.0);
	}

	@Test
	public void givenMinDistanceAndTime_ShouldReturnCorrectTotalFare() {
		CabServiceInvoice invoiceService = new CabServiceInvoice();
		double fare = invoiceService.calculateFare(0.5, 3);
		Assert.assertEquals(8, fare, 0.0);
	}

}
