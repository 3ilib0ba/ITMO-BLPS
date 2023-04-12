-- Заполняем возможные назначения блюда
insert into target values (1, 'breakfast');
insert into target values (2, 'dinner');
insert into target values (3, 'supper');
insert into target values (4, 'afternoon snack');
insert into target values (5, 'celebrity meal');

-- Заполняем возможные рубрики(категории)
insert into category values (1, 'soup');
insert into category values (2, 'second dish');
insert into category values (3, 'hot dish');
insert into category values (4, 'cold dish');
insert into category values (5, 'drink');


-- Заполняем возможные рубрики(категории)
insert into measure values (1, 'kg');
insert into measure values (2, 'gram');
insert into measure values (3, 'litres');
insert into measure values (4, 'tablespoon');


-- Заполняем один рецепт
    -- Добавим ингредиенты
insert into ingredient values (DEFAULT, 5, 'potato', 1);
insert into ingredient values (DEFAULT, 10, 'water', 3);
insert into ingredient values (DEFAULT, 950, 'makarony', 2);

    -- Добавим рецепт борща
insert into recipe values (DEFAULT, 'unbeliveable borsh', 'borsh', 'gigachad soup', 10, 100, 'Russia', null, null);

    -- Создадим связи
insert into recipe_category_relation values (DEFAULT, 1, 1);

insert into recipe_target_relation values (DEFAULT, 1, 2);
insert into recipe_target_relation values (DEFAULT, 1, 5);

insert into recipe_ingredient_relation values (DEFAULT, 1, 1);
insert into recipe_ingredient_relation values (DEFAULT, 1, 2);

-- Добавим рецепт макарон
insert into recipe values (DEFAULT, 'homemade makarony', 'makarony', 'if you have not anything', 15, 4, 'World', null, null);

-- Создадим связи
insert into recipe_category_relation values (DEFAULT, 2, 2);
insert into recipe_category_relation values (DEFAULT, 2, 3);

insert into recipe_target_relation values (DEFAULT, 2, 2);
insert into recipe_target_relation values (DEFAULT, 2, 3);

insert into recipe_ingredient_relation values (DEFAULT, 2, 3);
insert into recipe_ingredient_relation values (DEFAULT, 2, 2);


