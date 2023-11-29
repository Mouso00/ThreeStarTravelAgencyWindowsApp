package models;

import java.sql.Timestamp;

public class Reservation {
    private int reservation_id;
    private int user_id;
    private int passenger_id;
    private String train_number;
    private String class_type;
    private java.sql.Date date_of_journey;
    private String source_location;
    private String destination_location;
    private String status;
    private Timestamp created_at;
    private Integer closed_by_user_id;

    // Getters and Setters (omitted for brevity)
}

