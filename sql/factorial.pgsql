DROP FUNCTION IF EXISTS fact(integer);
DROP FUNCTION IF EXISTS populate_upto(integer);
DROP TABLE IF EXISTS factorial;

CREATE TABLE factorial (n integer, fact integer);

CREATE FUNCTION fact(n integer) RETURNS integer AS $$
DECLARE
BEGIN
        IF n <= 0 THEN
           RETURN 1;
        ELSE
           RETURN n * fact(n - 1);
        END IF;           
END;    
$$ language plpgsql;

CREATE FUNCTION populate_upto (n integer) RETURNS void AS $$
DECLARE
        i integer;
BEGIN
        i := 10;
        LOOP
           RAISE NOTICE 'Inserting values (%, %)', i, fact(i);
           INSERT INTO factorial VALUES (i, fact(i));
           i := i - 1;
           EXIT WHEN i = 0;
        END LOOP;
END;
$$ language plpgsql;

SELECT populate_upto(10);
SELECT * FROM factorial;
