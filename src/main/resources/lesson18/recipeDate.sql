set schema public;

create table IF NOT EXISTS RECIPE (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    stages VARCHAR(1000) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS UNIQUE_RECIPE ON RECIPE(name);

create table IF NOT EXISTS INGREDIENTS (
    id_recipe INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT cons_id_recipe FOREIGN KEY (id_recipe) references RECIPE(id) ON DELETE CASCADE
);