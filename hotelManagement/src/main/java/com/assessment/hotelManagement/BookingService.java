package com.assessment.hotelManagement;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

import entity.Booking;

public class BookingService {
	private static Configuration cfg=new Configuration().configure("in/cg/config/hibernate.cfg.xml");
	private static SessionFactory sf=cfg.buildSessionFactory();
	private static Session s=sf.openSession();


	
	
	
	//============================================Insert=============================================
	public void addBooking(Booking booking) {
		Transaction tr1=s.beginTransaction();
		try {
			s.persist(booking);
			tr1.commit();
			System.out.println("Booking added successfully");
			
		}catch(Exception e) {
			if(tr1!=null) {
				tr1.rollback();
			}
			System.out.println("Error while adding booking: " + e.getMessage());
		}
	}
	
	//=========================================View Bookings=========================================
	
	public void viewAllBooking() {

        try {

            List<Booking> list =
                    s.createQuery("from Booking", Booking.class).list();

            if (list.isEmpty()) {
                System.out.println("No Bookings Found!");
            }

            for (Booking b : list) {
                System.out.println(b.getBookingId() + " | "
                        + b.getCustomerName() + " | "
                        + b.getRoomType() + " | "
                        + b.getTotalAmount());
            }

        } catch (Exception e) {
            System.out.println("Error while fetching bookings: " + e.getMessage());
        }
    }
	
	
	
	//==========================================Update Booking=========================================
	
	public void updateBooking(int id, String newRoomType) {
		Transaction tr2=s.beginTransaction();
		Booking booking=s.get(Booking.class, id);
		try {
			if(booking!=null) {
				booking.setRoomType(newRoomType);
				s.merge(booking);
				tr2.commit();
				System.out.println("Booking Update Successful");
			}else {
				System.out.println("Booking not found");
			}
			}catch(Exception e) {
			      if(tr2!=null) {
			    	  tr2.rollback();
			      }
			      System.out.println("Update was unsuccessful");
			}
			
			
	}	
	
	
	//==========================================Delete Booking==========================================
	
	public void deleteBooking(int id) {
		Transaction tr3=s.beginTransaction();
		Booking booking=s.get(Booking.class, id);
		try {
			if(booking!=null) {
				s.remove(booking);
				tr3.commit();
				System.out.println("Booking info deleted successfully");
			}else {
				System.out.println("Bookin info not found");
			}
		}catch(Exception e) {
			if(tr3!=null) {
				tr3.rollback();
			}
			System.out.println("Error while deleting : "+e.getMessage());
		}
	}
	

	public void closeSession() {
        if (s!= null && s.isOpen()) {
            s.close();
        }
    }

}
