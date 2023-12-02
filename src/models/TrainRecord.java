package models;

public class TrainRecord {
    private int trainNumber;
    private String seatNumber;
    private String time;
    private double price;

    public TrainRecord() {
    	
    }
    public TrainRecord(int trainNumber, String seatNumber, String time, double price) {
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.time = time;
        this.price = price;
    }

    // Getters and setters (you can generate them using your IDE)
    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // You can override toString() for debugging or display purposes
    @Override
    public String toString() {
        return "TrainRecord{" +
                "trainNumber=" + trainNumber +
                ", seatNumber='" + seatNumber + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}
