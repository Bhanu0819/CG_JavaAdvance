package entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name="booking_details")
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;
	
	@Column(nullable=false)
	private String customerName;
	private String roomType;
	private String checkInDate;
	private String checkOutDate;
	private Double totalAmount;
	public Booking(String customerName, String roomType, String checkInDate, String checkOutDate) {
	
		this.customerName = customerName;
		this.roomType = roomType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		calculateTotalAmount();
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
		calculateTotalAmount();
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
		calculateTotalAmount();
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
		calculateTotalAmount();
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void calculateTotalAmount() {
		double pricePerDay=0;
		switch(roomType) {
		case "Standard" : pricePerDay=2000; break;
		case "Deluxe" : pricePerDay=3500; break;
		case "Suite" : pricePerDay=5000; break;
		default : throw new IllegalArgumentException("Invalid room type");
		
		}
		
		LocalDate in=LocalDate.parse(checkInDate);
		LocalDate out=LocalDate.parse(checkOutDate);
		
		long days=ChronoUnit.DAYS.between(in, out);
		
		this.totalAmount=days*pricePerDay;
	}
	
	
	

}
