CREATE TABLE roles(
	id serial,
	role_code varchar(10),
	role_name varchar(12),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE roles ADD CONSTRAINT role_pk
	PRIMARY KEY(id);
ALTER TABLE roles ADD CONSTRAINT role_bk
	UNIQUE(role_code);

CREATE TABLE users(
	id serial,
	role_id int,
	username varchar(30),
	"password" varchar(100),
	"email" varchar(100),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE users ADD CONSTRAINT users_pk
	PRIMARY KEY(id);
ALTER TABLE users ADD CONSTRAINT roles_fk
	FOREIGN KEY(role_id) REFERENCES roles(id);
ALTER TABLE users ADD CONSTRAINT users_bk
	UNIQUE(username, email);

CREATE TABLE company(
	id serial,
	company_name varchar(30),
	company_address varchar(50),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE company ADD CONSTRAINT company_pk
	PRIMARY KEY(id);

CREATE TABLE employee(
	id serial,
	user_id int,
	company_id int,
	nip varchar(6),
	employee_name varchar(30),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE employee ADD CONSTRAINT employee_pk
	PRIMARY KEY(id);
ALTER TABLE employee ADD CONSTRAINT user_fk
	FOREIGN KEY(user_id) REFERENCES users(id);
ALTER TABLE employee ADD CONSTRAINT company_fk
	FOREIGN KEY(company_id) REFERENCES company(id);
ALTER TABLE employee ADD CONSTRAINT employee_bk
	UNIQUE(nip);

CREATE TABLE asset_type(
	id serial,
	type_code varchar(10),
	type_name varchar(15),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE asset_type ADD CONSTRAINT type_pk
	PRIMARY KEY(id);
ALTER TABLE asset_type ADD CONSTRAINT type_bk
	UNIQUE(type_code);

CREATE TABLE asset_status(
	id serial,
	status_code varchar(10),
	status_name varchar(15),
	alt_name varchar(16),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE asset_status ADD CONSTRAINT status_pk
	PRIMARY KEY(id);
ALTER TABLE asset_status ADD CONSTRAINT status_bk
	UNIQUE(status_code);

CREATE TABLE brand(
	id serial,
	brand_name varchar(30),
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE brand ADD CONSTRAINT brand_pk
	PRIMARY KEY(id);

CREATE TABLE file(
	id serial,
	extension_name varchar(6),
	"content" bytea,
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

CREATE TABLE asset(
	id serial,
	company_id int,
	asset_type_id int,
	asset_status_id int,
	brand_id int,
	file_id int,
	asset_name varchar(20),
	serial varchar(10),
	asset_description TEXT,	
	invoice varchar(10),
	expired date,
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE asset ADD CONSTRAINT asset_pk
	PRIMARY KEY(id);
ALTER TABLE asset ADD CONSTRAINT company_fk
	FOREIGN KEY(company_id) REFERENCES company(id);
ALTER TABLE asset ADD CONSTRAINT type_fk
	FOREIGN KEY(asset_type_id) REFERENCES asset_type(id);
ALTER TABLE asset ADD CONSTRAINT status_fk
	FOREIGN KEY(asset_status_id) REFERENCES asset_status(id);
ALTER TABLE asset ADD CONSTRAINT brand_fk
	FOREIGN KEY(brand_id) REFERENCES brand(id);
ALTER TABLE asset ADD CONSTRAINT file_fk
	FOREIGN KEY(file_id) REFERENCES file(id);


CREATE TABLE checkout(
	id serial,
	asset_id int,
	employee_id int,
	other_asset_id int,
	place varchar(50),
	expired date,
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
);

ALTER TABLE checkout ADD CONSTRAINT checkout_pk
	PRIMARY KEY(id);
ALTER TABLE checkout ADD CONSTRAINT asset_fk
	FOREIGN KEY(asset_id) REFERENCES asset(id);
ALTER TABLE checkout ADD CONSTRAINT employee_fk
	FOREIGN KEY(employee_id) REFERENCES employee(id);
ALTER TABLE checkout ADD CONSTRAINT other_asset_fk
	FOREIGN KEY(other_asset_id) REFERENCES asset(id);


CREATE TABLE checkin(
	id serial,
	asset_status_id int,
	checkout_id int,
	created_by int,
	created_at timestamp WITHOUT time zone,
	updated_by int,
	updated_at timestamp WITHOUT time zone,
	"version" int,
	is_active boolean
	
);

ALTER TABLE checkin ADD CONSTRAINT checkin_pk
	PRIMARY KEY(id);
ALTER TABLE checkin ADD CONSTRAINT status_fk
	FOREIGN KEY(asset_status_id) REFERENCES asset_status(id);
ALTER TABLE checkin ADD CONSTRAINT checkout_fk
	FOREIGN KEY(checkout_id) REFERENCES checkout(id);