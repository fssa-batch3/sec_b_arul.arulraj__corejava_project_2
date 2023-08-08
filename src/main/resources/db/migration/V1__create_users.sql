USE techno_library;

CREATE TABLE category (
	id INT NOT NULL AUTO_INCREMENT,
	category_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE publisher (
	id INT NOT NULL AUTO_INCREMENT,
	publisher_name VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY (id)
);

CREATE TABLE book (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher_id INT NOT NULL,
    category_id INT NOT NULL,
    published_date DATE,
    price INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE book_stock (
	id INT NOT NULL AUTO_INCREMENT,
	book_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);