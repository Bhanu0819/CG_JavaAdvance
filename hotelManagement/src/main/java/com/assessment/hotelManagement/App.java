package com.assessment.hotelManagement;

import entity.Booking;
import java.util.List;
import java.util.*;


public class App {
    public static void main(String[] args) {
    	BookingService bs=new BookingService();
    	
    	List<Booking> bookings = Arrays.asList(
    	        new Booking("Rahul", "Deluxe", "2026-02-10", "2026-02-12"),
    	        new Booking("Anjali", "Suite", "2026-03-01", "2026-03-03"),
    	        new Booking("Kiran", "Standard", "2026-02-15", "2026-02-18"),
    	        new Booking("Sneha", "Deluxe", "2026-04-05", "2026-04-08"),
    	        new Booking("Vikram", "Suite", "2026-05-10", "2026-05-12")
    	);
    	for(Booking b:bookings) {
    	     bs.addBooking(b);
    	}
    	bs.viewAllBooking();
    	bs.updateBooking(2, "Suite");
    	bs.viewAllBooking();
    	bs.deleteBooking(4);
    	bs.viewAllBooking();
    	
    	
    	
    }
}
