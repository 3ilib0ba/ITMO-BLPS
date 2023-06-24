
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

    -- Добавим рецепт борща (последняя цифра = принадлежность к юзеру, которого нет в бд, но есть в xml)
insert into recipe values (DEFAULT, 'unbeliveable borsh', 'borsh', 'gigachad soup', 10, 100, 'Russia', null, null, 1);

    -- Создадим связи
insert into recipe_category_relation values (DEFAULT, 1, 1);

insert into recipe_target_relation values (DEFAULT, 1, 2);
insert into recipe_target_relation values (DEFAULT, 1, 5);

-- Добавим рецепт макарон
insert into recipe values (DEFAULT, 'homemade makarony', 'makarony', 'if you have not anything', 15, 4, 'World', null, null, 3);

-- Создадим связи
insert into recipe_category_relation values (DEFAULT, 2, 2);
insert into recipe_category_relation values (DEFAULT, 2, 3);

insert into recipe_target_relation values (DEFAULT, 2, 2);
insert into recipe_target_relation values (DEFAULT, 2, 3);

-- Добавим рецепт окрошки
insert into recipe values (DEFAULT, 'blizzard soup - okroshka', 'okroshka', 'strange soup', 5, 5, 'Moon', null, null, 2);

-- Создадим связи
insert into recipe_category_relation values (DEFAULT, 3, 2);
insert into recipe_category_relation values (DEFAULT, 3, 3);

insert into recipe_target_relation values (DEFAULT, 3, 2);
insert into recipe_target_relation values (DEFAULT, 3, 3);



-- Добавим ингредиенты
insert into ingredient values (DEFAULT, 5, 'potato', 1, 1);
insert into ingredient values (DEFAULT, 10, 'water', 3, 1);
insert into ingredient values (DEFAULT, 10, 'water', 3, 2);
insert into ingredient values (DEFAULT, 950, 'makarony', 2, 2);
insert into ingredient values (DEFAULT, 1, 'kolbasa', 1, 3);
insert into ingredient values (DEFAULT, 1, 'ukrop', 1, 3);
insert into ingredient values (DEFAULT, 1, 'kvas', 3, 3);


--Добавим комменты
insert into comments values (DEFAULT, 'Wow how delicious', 3, 1);
insert into comments values (DEFAULT, 'This Borsh is the food of the gods', 1, 2);
insert into comments values (DEFAULT, 'this pasta is swesome', 2, 3);
