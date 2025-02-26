CREATE TABLE IF NOT EXISTS members (
                                       mno INT AUTO_INCREMENT PRIMARY KEY,
                                       userid VARCHAR(18) UNIQUE NOT NULL,
                                       passwd VARCHAR(64) NOT NULL,
                                       name VARCHAR(50) NOT NULL,
                                       email VARCHAR(100) NOT NULL,
                                       regdate DATETIME DEFAULT CURRENT_TIMESTAMP
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS boards (
                                      bno INT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(128) NOT NULL,
                                      userid VARCHAR(18) NOT NULL,
                                      regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
                                      thumbs INT DEFAULT 0,
                                      views INT DEFAULT 0,
                                      contents TEXT NOT NULL,
                                      FOREIGN KEY (userid) REFERENCES members (userid) ON DELETE CASCADE
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;