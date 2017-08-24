-- name: get-cats
-- Counts all the cats.
SELECT *
FROM cat

-- name: create-guestbook
-- Inserts all the cats.
CREATE TABLE guestbook (
    id id SERIAL UNIQUE PRIMARY KEY,
    timestamp DEFAULT CURRENT_TIMESTAMP,
    name TEXT,
    message TEXT
)

-- name: create-index
CREATE INDEX timestamp_index ON guestbook (timestamp)

-- name: add-message
INSERT INTO guestbook (timestamp, name, message)
VALUES ( :timestamp , :name, :message )
RETURNING :message

-- name: get-messages
-- Counts all the cats.
SELECT *
FROM guestbook ORDER BY timestamp DESC

-- name: get-message-byid
SELECT *
FROM guestbook
WHERE id = :id

-- name: get-max-id
-- Gives the last id, so there will not be duplicates
SELECT id
FROM guestbook
WHERE id = (select max(id) from guestbook)
