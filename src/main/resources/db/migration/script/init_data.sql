INSERT INTO roles (role_code, role_name, created_by, created_at, version, is_active) VALUES
('SA','Super Admin', 1, NOW(), 0, true),
('NA','Non Admin', 1, NOW(), 0, true);

INSERT INTO users(role_id, username, "password", "email", created_by, created_at, version, is_active) VALUES
(1, 'username1', 'pass1', 'user1@mail.com', 1, NOW(), 0, true),
(2, 'username2', 'pass2', 'user2@mail.com', 1, NOW(), 0, true),
(2, 'username3', 'pass3', 'user3@mail.com', 1, NOW(), 0, true),
(2, 'username4', 'pass4', 'user4@mail.com', 1, NOW(), 0, true),
(2, 'username5', 'pass5', 'user5@mail.com', 1, NOW(), 0, true);

INSERT INTO company (company_name, company_address, created_by, created_at, version, is_active) VALUES
('PT. A', 'Jl. Merdeka A', 1, NOW(), 0, true),
('PT. B', 'Jl. Merdeka B', 1, NOW(), 0, true),
('PT. C', 'Jl. Merdeka C', 1, NOW(), 0, true),
('PT. D', 'Jl. Merdeka D', 1, NOW(), 0, true),
('PT. E', 'Jl. Merdeka E', 1, NOW(), 0, true);

INSERT INTO employee (user_id, company_id, nip, employee_name, created_by, created_at, version, is_active) VALUES
(1, 1, 'AA1111', 'Mr. A1', 1, NOW(), 0, true),
(2, 2, 'BB1111', 'Mr. B1', 1, NOW(), 0, true),
(3, 1, 'CC1111', 'Mr. A2', 1, NOW(), 0, true),
(4, 3, 'DD1111', 'Mr. C1', 1, NOW(), 0, true),
(5, 4, 'EE1111', 'Mr. D1', 1, NOW(), 0, true);

INSERT INTO asset_type (type_code, type_name, created_by, created_at, version, is_active) VALUES
('GE', 'general', 1, NOW(), 0, true),
('LI', 'licenses', 1, NOW(), 0, true),
('CP', 'components', 1, NOW(), 0, true),
('CS', 'consumables', 1, NOW(), 0, true);

INSERT INTO asset_status (status_code, status_name, alt_name, created_by, created_at, version, is_active) VALUES
('RD', 'deployable', 'ready to deploy', 1, NOW(), 0, true),
('BR', 'undeployable', 'broken', 1, NOW(), 0, true),
('LS', 'archived', 'lost/stolen', 1, NOW(), 0, true),
('PE', 'pending', 'pending', 1, NOW(), 0, true),
('RE', 'undeployable', 'repair', 1, NOW(), 0, true);

INSERT INTO brand (brand_name, created_by, created_at, version, is_active) VALUES
('Super 1', 1, NOW(), 0, true),
('Super 2', 1, NOW(), 0, true),
('Super 3', 1, NOW(), 0, true),
('Super 4', 1, NOW(), 0, true),
('Super 5', 1, NOW(), 0, true);