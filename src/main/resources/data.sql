INSERT INTO recipe (id, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
1,
'1Paprika - Chicorée - Salat', 
'Sehr einfach und lecker', 
'Die Paprikaschoten vierteln, dünn schälen (geht gut mit einem Sparschäler), Kerne und Trennwände entfernen und die Paprikaviertel in Streifen schneiden. 
Den Chicorée putzen und klein schneiden. Äpfel schälen, vierteln, Kerngehäuse entfernen und die Apfelviertel in Scheiben schneiden.
Für die Soße: Joghurt, Sahne, Orangensaft, Essig, Zucker, Chili, Salz und 2 Messerspitzen abgeriebene Zitronenschale mit dem Schneebesen verrühren. 
Die Soße über die vorbereiteten Salatzutaten geben und mit in Öl gerösteten Pinienkernen bestreuen.', 
'Pasta', 
1, 
133.00, 
'normal',  
1500, 
2700, 
600);

INSERT INTO recipe (id, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
2,
'1Paprika - Chicorée - Salat', 
'Sehr einfach und lecker', 
'Die Paprikaschoten vierteln, dünn schälen (geht gut mit einem Sparschäler), Kerne und Trennwände entfernen und die Paprikaviertel in Streifen schneiden. 
Den Chicorée putzen und klein schneiden. Äpfel schälen, vierteln, Kerngehäuse entfernen und die Apfelviertel in Scheiben schneiden.
Für die Soße: Joghurt, Sahne, Orangensaft, Essig, Zucker, Chili, Salz und 2 Messerspitzen abgeriebene Zitronenschale mit dem Schneebesen verrühren. 
Die Soße über die vorbereiteten Salatzutaten geben und mit in Öl gerösteten Pinienkernen bestreuen.', 
'Pasta', 
1, 
133.00, 
'normal',  
1500, 
2700, 
600);



INSERT INTO image (id, image_name, image_url, recipe_id) VALUES (1, 'Bild', 'www.test.de', 1);

INSERT INTO ingredient (id, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (9, 'Zwiebel', 2.5, 'Stück', 1);
INSERT INTO ingredient (id, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (2, 'Kartoffel', 1, 'Stück', 1);
INSERT INTO ingredient (id, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (3, 'Apfel', 3, 'Stück', 1);


-- INSERT INTO ingredient (id, image_name, image_url, recipe_id) VALUES (1, 'Bild', 'www.test.de', 1);
--     @EqualsAndHashCode.Include
--     private String id;

--     @Column
--     @EqualsAndHashCode.Include
--     private String name;
    
--     @Column
--     private Double quantity;

--     @Column
--     private  String unit;
