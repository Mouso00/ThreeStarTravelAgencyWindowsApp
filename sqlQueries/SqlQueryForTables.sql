-- Show all tables in the database
SHOW TABLES;

-- Show columns for the 'users' table
DESCRIBE users;

DESCRIBE train;

-- Show columns for the 'passengers' table
DESCRIBE passengers;

-- Show columns for the 'reservations' table
DESCRIBE reservations;

-- Show columns for the 'cancellations' table
DESCRIBE cancellations;

-- Show columns for the 'pnr_enquiries' table
DESCRIBE pnr_enquiries;

-- Show columns for the 'follows' table
DESCRIBE follows;

-- Show columns for the 'posts' table
DESCRIBE posts;



-- Check if the foreign key constraint exists
SELECT
    CONSTRAINT_NAME
FROM
    INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS
WHERE
    CONSTRAINT_NAME = 'fk_closed_by_user' AND
    TABLE_NAME = 'reservations';

-- If it exists, drop the foreign key constraint
ALTER TABLE reservations DROP FOREIGN KEY fk_closed_by_user;

-- Perform the ALTER TABLE statement
ALTER TABLE users MODIFY COLUMN id INT AUTO_INCREMENT;

-- Add the foreign key constraint back
ALTER TABLE reservations ADD CONSTRAINT fk_closed_by_user FOREIGN KEY (closed_by_user_id) REFERENCES users(id);

-- Perform the ALTER TABLE statement
ALTER TABLE users MODIFY COLUMN id INT AUTO_INCREMENT;


-- Step 1: Drop the foreign key constraint in 'cancellations' table
ALTER TABLE cancellations DROP FOREIGN KEY fk_closed_by_user_cancellation;

-- Step 2: Modify the 'id' column in 'users' table
ALTER TABLE users MODIFY COLUMN id INT AUTO_INCREMENT;

-- Step 3: Recreate the foreign key constraint in 'cancellations' table
ALTER TABLE cancellations
ADD CONSTRAINT fk_closed_by_user_cancellation
FOREIGN KEY (user_id) REFERENCES users(id);



CREATE TABLE trains (
    train_id INT AUTO_INCREMENT PRIMARY KEY,
    train_number INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    departure_time DATETIME NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);


-- Add sample data to the trains table
INSERT INTO trains (train_number, seat_number, departure_time, price) VALUES
(12345, '12A', '2023-12-01 10:00:00', 50.00),
(54321, '7B', '2023-12-02 14:30:00', 60.00);
