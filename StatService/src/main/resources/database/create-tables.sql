create table if not exists recipe_count_views
(
    recipe_id         int primary key
        references recipe (id),
    count_of_views    int not null
        check ( count_of_views >= 0 )
);
