package models;

public class Reservation {
	 private int trainId;
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
    private String userId;
    private int generatedPnr;

    public Reservation() {
    	
    }

    public Reservation(String userId, String from, String to, String date, String time, String travelClass) {
    	  this.userId = userId;
//    	this.trainNumber = trainNumber;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.travelClass = travelClass;
//        this.seat = seat;
//        this.price = price;
//        this.status = status;
      
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
    public int getGenaretedPnr() {
		return generatedPnr;
		
	}
	public void setGenaretedPnr(int generatedPnr) {
		this.generatedPnr = generatedPnr;
		
	}

	public Object getCreatedAt() {
		// TODO Auto-generated method stub
		return null;
	}
}
