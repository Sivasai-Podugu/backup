-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Drop the student table if it exists
DROP TABLE IF EXISTS user, note, tag, note_tags;


-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;



CREATE TABLE user(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE note (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       user_id INT NOT NULL,
                       title VARCHAR(100) NOT NULL,
                       content TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE tag (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE note_tags (
                           note_id INT NOT NULL,
                           tag_id INT NOT NULL,
                           PRIMARY KEY (note_id, tag_id),
                           FOREIGN KEY (note_id) REFERENCES note (id),
                           FOREIGN KEY (tag_id) REFERENCES tag(id)
);


