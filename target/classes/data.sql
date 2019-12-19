DROP TABLE IF EXISTS moneda;
 
CREATE TABLE moneda (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL,
  tipo VARCHAR(250) NOT NULL
);
 
INSERT INTO moneda (nombre, tipo) VALUES
  ('soles', '0.3'),
  ('euros', '1.11'),
  ('dolares', '1');
