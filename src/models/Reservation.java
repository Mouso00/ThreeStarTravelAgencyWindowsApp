package models;

public class Reservation {
	 private int reservationId;
    private String from;
    private String to;
    private String date;
    private String time;
    private String travelClass;
    private String seat;
    private double price;
    private String status; // Assuming there's a status field

    public Reservation(String from, String to, String date, String time, String travelClass, String selectedSeat, double price, int userId) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.travelClass = travelClass;
        this.seat = selectedSeat;
        this.price = price;
    }
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    // Getter methods
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public String getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
