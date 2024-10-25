create table tile (
    id varchar primary key unique not null,
    height double precision not null,
    width double precision not null,
    area double precision
);


create table users(
    id varchar primary key unique not null,
    name varchar not null unique,
    email varchar not null unique,
    password varchar not null
);

create table operations(
    id varchar primary key unique not null,
    name varchar unique not null,
    loss_perc double precision not null,
    total_tiles double precision not null,
    final_tiles double precision not null
);

create table history(
    id varchar primary key unique not null,
    id_operations varchar,
    id_user varchar,

    foreign key (id_operations) references operations(id) on delete cascade,
    foreign key (id_user) references users(id)
);