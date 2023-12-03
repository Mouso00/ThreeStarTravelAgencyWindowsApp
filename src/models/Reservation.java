package models;

public class Reservation {
	private int id;
	private int trainNumber;
    private String from;
    private String to;
    private String date;
    private String time;
    private String travelClass;
    private String seat;
    private double price;
    private String status;
	private String closedByUserId;

    public Reservation() {
    }

    public Reservation(int id,int trainNumber, String from, String to, String date, String time, String travelClass, String selectedSeat, double price, String userId) {
        this.id =id;
        this.trainNumber = trainNumber;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.travelClass = travelClass;
        this.seat = selectedSeat;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void getId(int id) {
        this.id = id;
    }
 
    public String getFrom() {
        return from;
    }

    public void setFrom(String selectedFrom) {
        this.from = selectedFrom;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String selectedTo) {
        this.to = selectedTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String selectedDate) {
        this.date = selectedDate;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String selectedTime) {
        this.time = selectedTime;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String selectedClass) {
        this.travelClass = selectedClass;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String selectedSeat) {
        this.seat = selectedSeat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double calculatePrice) {
        this.price = calculatePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String string) {
        this.status = string;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

  
}
