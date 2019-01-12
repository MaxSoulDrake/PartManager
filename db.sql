USE test;

DROP TABLE IF EXISTS parts;
CREATE TABLE parts
(
  id int(10) PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  required BIT DEFAULT false NOT NULL,
  quantity int(7) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;
CREATE UNIQUE INDEX parts_title_uindex ON parts (title);

INSERT INTO `parts` (`title`,`required`,`quantity`)
    VALUES
    ("Motherboard", 1, 10),
    ("Sound card", 0, 5),
    ("Processor", 1, 7),
    ("Case lighting", 0, 3),
    ("HDD", 0, 8),
    ("SSD", 1, 6),
    ("Case", 1, 15),
    ("Memory", 1, 12),
    ("Video card", 0, 10),
    ("Computer cooling", 1, 5),
    ("DVD/CD drive", 0, 11),
    ("BD drive", 0, 2),
    ("Mouse", 0, 12),
    ("Keyboard", 0, 9),
    ("Speakers", 0, 2),
    ("Scanner", 0, 0),
    ("Printer", 0, 1),
    ("Monitor", 0, 6),
    ("Headphones", 0, 17),
    ("Power cable", 1, 14),
    ("Power supply", 1, 8),
    ("Microphone", 0, 0),
    ("Card reader", 0, 0),
    ("Network adapter", 0, 5),
    ("USB-hub", 0, 7),
    ("Webcam", 0, 5),
    ("Router", 0, 11),
    ("TV tuner", 0, 0);