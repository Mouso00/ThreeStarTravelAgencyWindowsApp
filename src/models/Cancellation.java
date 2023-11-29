package models;

import java.sql.Timestamp;

public class Cancellation {
    private int cancellation_id;
    private int user_id;
    private int passenger_id;
    private String pnr_number;
    private String details;
    private Timestamp created_at;
    private Integer closed_by_user_id;

    // Getters and Setters (omitted for brevity)
}

