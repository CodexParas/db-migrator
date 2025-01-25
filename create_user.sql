-- Switch to the pluggable database
ALTER SESSION SET CONTAINER = dbmigrator;

-- Create the `dbmigrator` user
CREATE USER dbmigrator IDENTIFIED BY dbmigrator;

-- Grant necessary privileges
GRANT CONNECT, RESOURCE TO dbmigrator;

-- Verify user creation
SELECT username
FROM all_users
WHERE username = 'DBMIGRATOR';

ALTER USER dbmigrator QUOTA UNLIMITED ON SYSTEM;