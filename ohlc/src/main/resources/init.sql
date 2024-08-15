-- Create a new user
CREATE USER userohlc WITH PASSWORD 'password123';

-- Grant privileges or roles to the user
GRANT CONNECT ON DATABASE ohlc TO userohlc;
GRANT USAGE ON SCHEMA public TO userohlc;

