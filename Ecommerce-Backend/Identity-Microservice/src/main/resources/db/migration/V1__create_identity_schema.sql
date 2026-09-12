CREATE TABLE roles (
                       role_id BIGINT NOT NULL AUTO_INCREMENT,
                       role_type VARCHAR(255) NOT NULL,
                       description VARCHAR(255),
                       created_at DATETIME(6) NOT NULL,
                       updated_at DATETIME(6) NOT NULL,

                       CONSTRAINT pk_roles
                           PRIMARY KEY (role_id),

                       CONSTRAINT uk_roles_role_type
                           UNIQUE (role_type)
);

CREATE TABLE users(
                      user_id BIGINT NOT NULL AUTO_INCREMENT,
                      first_name VARCHAR(50) NOT NULL,
                      last_name VARCHAR(50),
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      phone_number VARCHAR(15) NOT NULL,
                      enabled BIT NOT NULL,
                      created_at DATETIME(6) NOT NULL,
                      updated_at DATETIME(6) NOT NULL,

                      CONSTRAINT pk_users
                          PRIMARY KEY (user_id),

                      CONSTRAINT uk_users_email
                          UNIQUE (email)
);

CREATE TABLE addresses (
                           address_id BIGINT NOT NULL AUTO_INCREMENT,
                           full_name VARCHAR(50) NOT NULL,
                           mobile_number VARCHAR(15) NOT NULL,
                           address_line1 VARCHAR(255) NOT NULL,
                           address_line2 VARCHAR(255),
                           city VARCHAR(255) NOT NULL,
                           state VARCHAR(255) NOT NULL,
                           country VARCHAR(255) NOT NULL,
                           postal_code VARCHAR(255) NOT NULL,
                           address_type VARCHAR(255) NOT NULL,
                           is_default BIT NOT NULL,
                           created_at DATETIME(6) NOT NULL,
                           updated_at DATETIME(6) NOT NULL,
                           user_id BIGINT NOT NULL,

                           CONSTRAINT pk_addresses
                               PRIMARY KEY (address_id),

                           CONSTRAINT fk_addresses_user
                               FOREIGN KEY (user_id)
                                   REFERENCES users(user_id)
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,

                            CONSTRAINT pk_user_roles
                                PRIMARY KEY (user_id, role_id),

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(user_id),

                            CONSTRAINT fk_user_roles_role
                                FOREIGN KEY (role_id)
                                    REFERENCES roles(role_id)
);

CREATE INDEX idx_addresses_user_id
    ON addresses(user_id);


CREATE INDEX idx_user_roles_role_id
    ON user_roles(role_id);
