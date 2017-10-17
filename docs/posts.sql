create table posts(
	id SERIAL PRIMARY KEY,
    address varchar(256),
    area integer,
    house_type varchar(256),
    num_rooms integer,
    floor integer,
    price bigint,
    description varchar(256),
    year bigint,
    creation_date timestamp,
    phone varchar(256),
    image_url varchar(1024),
    archived boolean default false
);