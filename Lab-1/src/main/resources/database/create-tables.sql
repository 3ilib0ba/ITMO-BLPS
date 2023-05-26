create table if not exists recipe
(
    id                serial primary key,
    short_description text not null,
    heading           text not null,
    author_comment    text,
    cooking_time      int,
    serving_number    int,
    cuisine           varchar(50),
    main_photo        text,
    video_url         text,
    user_id           int not null
);

create table if not exists category
(
    id   serial primary key,
    name text not null unique
);

create table if not exists recipe_category_relation
(
    id          serial primary key,
    recipe_id   int not null
        references recipe (id),
    category_id int not null
        references category (id)
);

create table if not exists target
(
    id   serial primary key,
    name text not null unique
);

create table if not exists recipe_target_relation
(
    id        serial primary key,
    recipe_id int not null
        references recipe (id),
    target_id int not null
        references target (id)
);

create table if not exists tags
(
    id   serial primary key,
    name text not null unique
);

create table if not exists recipe_tags_relation
(
    id        serial primary key,
    recipe_id int not null
        references recipe (id),
    tags_id   int not null
        references tags (id)
);

create table if not exists measure
(
    id   serial primary key,
    name text not null unique
);

create table if not exists ingredient
(
    id         serial primary key,
    count      int not null,
    name       varchar(50) not null,
    measure_id int not null
        references measure (id),
    recipe_id  int not null
        references recipe (id)
);

create table if not exists comments
(
    id          serial primary key,
    text        text not null,
    recipe_id   int not null
        references recipe (id),
    user_id     int
);


