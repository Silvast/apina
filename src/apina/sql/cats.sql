-- name: get-cats
-- Counts all the cats.
SELECT *
FROM cat

-- name: create-guestbook
-- Inserts all the cats.
CREATE TABLE guestbook (
    id INTEGER PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name TEXT,
    message TEXT
)

-- name: create-index
CREATE INDEX timestamp_index ON guestbook (timestamp)

-- name: add-message
INSERT INTO guestbook (id, timestamp, name, message)
VALUES ( :id, :timestamp , :name, :message )

-- name: get-messages
-- Counts all the cats.
SELECT *
FROM guestbook