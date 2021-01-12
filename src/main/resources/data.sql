
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
1,
RANDOM_UUID(),
'Fruchtiger Feldsalat mit Avocado-Reisbällchen', 
'Fruchtig und Lecker', 
'Für die Reisbällchen den Reis bissfest kochen. Dann mit der halben Avocado und dem Mehl zu einer leicht klebrigen Masse kneten und mit Paprika, Kurkuma und Salz und Pfeffer würzen. Den Teig zu kleinen Kügelchen formen (evtl. etwas mehr Mehl dazugeben, wenn sie nicht ganz halten) und in etwas Olivenöl ausbacken. Zwischenzeitlich die Zwiebeln in kleine Ringe, die halbe Avocado und die halbe Mango in kleine Würfel schneiden. Den Feldsalat waschen, mit Avocado, Mango und Zwiebeln garnieren. Für das Dressing Olivenöl, Balsamico, Senf und Honig verquirlen und gut pfeffern. Das Dressing über dem Salat verteilen und zum Schluss mit ein paar noch warmen Reisbällchen servieren.', 
'Snacks und kleine Gerichte', 
1, 
544.00, 
'normal',  
1800, 
0, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fruchtiger Feldsalat mit Avocado-Reisbällchen', 'https://img.chefkoch-cdn.de/rezepte/2490181391546547/bilder/660454/crop-960x640/jankos-fruchtiger-feldsalat-mit-avocado-reisbaellchen.jpg', 1);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fruchtiger Feldsalat mit Avocado-Reisbällchen', 'https://img.chefkoch-cdn.de/rezepte/2490181391546547/bilder/952335/crop-960x640/jankos-fruchtiger-feldsalat-mit-avocado-reisbaellchen.jpg', 1);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fruchtiger Feldsalat mit Avocado-Reisbällchen', 'https://img.chefkoch-cdn.de/rezepte/2490181391546547/bilder/1004312/crop-960x640/jankos-fruchtiger-feldsalat-mit-avocado-reisbaellchen.jpg', 1);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Feldsalat', 150, 'Gramm', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zwiebel', 1, 'Stück', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Mango', 0.5, 'Stück', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Avocado', 0.5, 'Stück', 1);


INSERT INTO image (uuid, image_name, image_url, ingredient_id) VALUES (RANDOM_UUID(), 'Testzutat', 'www.egal.de', 1);

INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Reis', 80, 'Gramm', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Mehl', 2, 'Esslöffel ', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Paprikapulver, rosenscharf', 1, 'Messerspitze  ', 1);

INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Olivenöl', 6, 'Esslöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Balsamico', 4, 'Esslöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Honig', 2, 'Teelöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Senf', 2, 'Teelöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Pfeffer', 1, 'Messerspitze', 1);



-- Model for https://www.chefkoch.de/rezepte/1501691255764679/Fettarmer-Doener-mit-Putenfleisch.html 
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
2,
RANDOM_UUID(),
'Fettarmer Döner mit Putenfleisch', 
'Besser und gesünder als beim Dönermann', 
'Zuerst die Putenbrustfilets waschen, in kleine Streifen schneiden und in eine Schüssel geben. Mit Salz, Pfeffer, Cayennepfeffer, Curry und Paprikapulver würzen. Dann das Öl dazugeben und mit den Händen alles gut vermischen. Die Schüssel für etwa eine halbe Stunde in den Kühlschrank stellen. Währenddessen die Soße zubereiten. Dafür den Joghurt ebenfalls in eine Schüssel geben. Mit Salz, Pfeffer, Curry, Zitronensaft und - wer möchte - auch mit gehacktem Schnittlauch abschmecken und kalt stellen. Jetzt eine Pfanne heiß werden lassen und das Fleisch darin ca. 3 Minuten braten. Fett in die Pfanne zu geben ist unnötig, da in der Marinade schon Öl enthalten ist. Den Eisbergsalat und den Kohl möglichst klein schneiden. Die Tomaten und die Gurke ebenso wie den Feta-Käse in Scheiben schneiden. Das große Fladenbrot vierteln und für 3 - 5 Minuten in den vorgeheizten Ofen (ca. 200 °C Umluft) legen. Anschließend rausnehmen, aufschneiden und mit dem vorbereiteten Gemüse, den Putenbruststreifen und der Soße abwechselnd füllen.', 
'Hauptspeise', 
1, 
702, 
'normal',  
900, 
300, 
1800);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fettarmer Döner mit Putenfleisch', 'https://img.chefkoch-cdn.de/rezepte/1501691255764679/bilder/945080/crop-960x640/fettarmer-doener-mit-putenfleisch.jpg', 2);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fettarmer Döner mit Putenfleisch', 'https://img.chefkoch-cdn.de/rezepte/1501691255764679/bilder/1025171/crop-960x640/fettarmer-doener-mit-putenfleisch.jpg', 2);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fettarmer Döner mit Putenfleisch', 'https://img.chefkoch-cdn.de/rezepte/1501691255764679/bilder/1292234/crop-960x640/fettarmer-doener-mit-putenfleisch.jpg', 2);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(),  'Fladenbrot', 400, 'Gramm', 2);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(),  'Putenbrustfilet', 3, 'Stück', 2);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(),  'Eisbergsalat', 6, 'Blätter', 2);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(),  'Weißkohl', 0.25, 'Kopf', 2);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Tomate', 2, 'Stück', 2);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Gurke', 0.25, 'Stück ', 2);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Feta-Käse', 100, 'Gramm', 2);

INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Olivenöl', 2, 'Esslöffel', 2);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Currypulver', 1, 'Esslöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Paprikapulver', 2, 'Esslöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Cayennepfeffer', 2, 'Esslöffel', 1);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Salz und Pfeffer', 1, 'Messerspitze', 1);

-- Model for https://www.chefkoch.de/rezepte/1885731306675038/Vollkornspaghetti-mit-Paprikapesto.html 
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
3,
RANDOM_UUID(),
'Vollkornspaghetti mit Paprikapesto', 
'', 
'Die Paprikaschoten waschen, halbieren, Strunk und Kerne entfernen, vierteln und auf ein Backblech legen. Im vorgeheizten Backofen bei ca. 200°C 10 - 20 min. rösten. In der Zwischenzeit die Pinienkerne in einer Pfanne ohne Fett vorsichtig rösten (wenn sie zu schwarz werden, schmeckt das Pesto am Ende ziemlich verbrannt). Das Salzwasser für die Nudeln aufsetzen. Basilikum, Parmesan, Pinienkerne und Knoblauch in eine hohe Schüssel geben. Wenn die Paprikaschoten fertig sind, kann man die Haut abziehen (ich lasse sie immer dran) und etwas abkühlen lassen. Während die Spaghetti im kochenden Salzwasser garen, werden die Paprikaschoten mit den restlichen festen Zutaten mit einem Pürierstab oder einem Standmixer püriert und das Olivenöl langsam hinzugegeben. Ein Spritzer Balsamico hinzugeben, salzen und pfeffern nach Geschmack. Die Nudeln werden abgegossen, wenn die gewünschte Bissfestigkeit erreicht ist und wieder in den Topf gefüllt. Das Pesto mit den Nudeln gut vermischen und auf dem Teller anrichten.', 
'Hauptspeise', 
1, 
509.00, 
'simpel',  
1500, 
2100, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Vollkornspaghetti mit Paprikapesto', 'https://img.chefkoch-cdn.de/rezepte/1885731306675038/bilder/956610/crop-960x640/vollkornspaghetti-mit-paprikapesto.jpg', 3);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Vollkornspaghetti mit Paprikapesto', 'https://img.chefkoch-cdn.de/rezepte/1885731306675038/bilder/1005705/crop-960x640/vollkornspaghetti-mit-paprikapesto.jpg', 3);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Vollkornspaghetti mit Paprikapesto', 'https://img.chefkoch-cdn.de/rezepte/1885731306675038/bilder/1131209/crop-960x640/vollkornspaghetti-mit-paprikapesto.jpg', 3);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Rote Paprikaschote', 1, 'Stück', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Basilikum', 0.5, 'Handvoll', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Pinienkerne', 1, 'Teelöffel', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Parmesan, frisch gerieben', 1, 'Esslöffel', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Knoblauch, klein gehackt', 0.5, 'Zehe', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Olivenöl', 1, 'Esslöffel', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Balsamico bianco', 1, 'Spritzer', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Salz und Pfeffer', 1, 'Messerspitze', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Spaghetti, vollkorn', 100, 'Gramm', 3);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Salzwasser', 500, 'Milliliter', 3);


-- Model for https://www.chefkoch.de/rezepte/2697401422549505/Vegetarischer-Kuerbis-Burger.html
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
4,
RANDOM_UUID(),
'Vegetarischer Kürbis-Burger', 
'Schnell und Gesund', 
'Zunächst den Kürbis waschen, aushöhlen und das Fruchtfleisch in gleich große Würfel schneiden, bei Hokkaidokürbissen darf die Schale dran bleiben. Dann die Kürbiswürfel mit Olivenöl, etwas Zimt, Muskat sowie Salz und Pfeffer marinieren. 25 Minuten im vorgeheizten Ofen bei 180 °C Ober-/Unterhitze backen, bis der Kürbis weich wird. Währenddessen die Brötchen aufschneiden, mit Ziegenkäse und Feigen belegen und für 5 Minuten mit in den Ofen geben. Zum Schluss die Brötchenhälften mit den Kürbiswürfeln belegen. Dazu passt ein herbstlicher Feldsalat oder Pastinaken-Pommes.', 
'Hauptspeise', 
1, 
354.0, 
'simpel',  
601, 
1500, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Vegetarischer Kürbis-Burger', 'https://img.chefkoch-cdn.de/rezepte/2697401422549505/bilder/944428/crop-960x640/vegetarischer-kuerbis-burger.jpg', 4);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Vegetarischer Kürbis-Burger', 'https://img.chefkoch-cdn.de/rezepte/2697401422549505/bilder/953546/crop-960x640/vegetarischer-kuerbis-burger.jpg', 4);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Vegetarischer Kürbis-Burger', 'https://img.chefkoch-cdn.de/rezepte/2697401422549505/bilder/842275/crop-960x640/vegetarischer-kuerbis-burger.jpg', 4);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Hokkaidokürbis', 1, 'Stück', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Ziegenkäse', 200, 'Gramm', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Frische Feige', 100, 'Gramm', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Brötchen, am besten Körnerecken', 1, 'Stück', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Olivenöln', 1, 'Spritzer', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zimt', 1, 'Messerspitze', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Muskat', 1, 'Messerspitze', 4);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), '	Salz und Pfeffer', 1, 'Messerspitze', 4);


-- Model for https://www.chefkoch.de/rezepte/2697401422549505/Vegetarischer-Kuerbis-Burger.html
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
5,
RANDOM_UUID(),
'Apfelpfannkuchen', 
'Leckeres Frühstück', 
'Aus Mehl, Milch, Eigelb, zerbröckelter Hefe, Zimt und 2 EL Zucker einen Teig rühren. Teig 30 Min. gehen lassen. Ich löse die Hefe meist nicht auf, sondern zerbrösele sie in feine Stücke und arbeite sie mit der Küchenmaschine in den Teig ein. Man kann sie natürlich auch in etwas warmer Milch auflösen und dann zum Teig geben. Nach Ende der Gehzeit Eiweiß mit 1 EL Zucker steif schlagen und unterheben. Äpfel schälen, entkernen (Apfelbohrer) und quer in dünne Scheiben schneiden. Butter in einer beschichteten Pfanne erhitzen und jeweils drei kleine Pfannkuchen (ca. 2 EL Teig) in die Pfanne setzen. Diese mit jeweils 1-2 Apfelringen belegen. Wenn die Unterseite fest ist, wenden und die Apfelseite kurz braten. Mit Zimt-Zucker bestreut servieren.',
'Süßspeise', 
1, 
522.00, 
'simpel',  
1200, 
0, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Apfelpfannkuchen', 'https://img.chefkoch-cdn.de/rezepte/814111185712614/bilder/657441/crop-960x640/apfelpfannkuchen.jpg', 5);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Apfelpfannkuchen', 'https://img.chefkoch-cdn.de/rezepte/814111185712614/bilder/654882/crop-960x640/apfelpfannkuchen.jpg', 5);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Apfelpfannkuchen', 'https://img.chefkoch-cdn.de/rezepte/814111185712614/bilder/727861/crop-960x640/apfelpfannkuchen.jpg', 5);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Mehl', 65, 'Gramm', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Milch', 75, 'Milliliter', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Eigelb', 1, 'Stück', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Frische Hefe', 5, 'Gramm', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zimt', 1, 'Teelöffel', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zucker', 1, 'Esslöffel', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Apfel', 1, 'Stück', 5);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Butter', 50, 'Gramm', 5);



-- Model for https://www.chefkoch.de/magazin/artikel/3390,0/Chefkoch/Gesunde-Rezepte-von-IN-FORM-empfohlen.html#gesunde-rezepte-f-r-das-fr-hst-ck_35944_5
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
6,
RANDOM_UUID(),
'Himbeer-Pfirsich-Smoothie', 
'Mit echter Vanille', 
'Die Pfirsiche kreuzweise einritzen und mit kochendem Wasser übergießen. Jetzt kann die Haut problemlos abgezogen werden. Das Vanillemark aus der halben Schote herauskratzen. Alle Zutaten kommen jetzt miteinander in den Mixer. Erst einmal kurz auf der kleinsten Stufe pürieren, dann auf der höchsten. Sollte der Smoothie doch etwas zu dickflüssig geworden sein, einfach noch ein bisschen Milch dazugeben. Gießt den Smoothie in 2 Gläser, dekoriert nach Belieben und genießt ihn.',
'Getränk', 
1, 
115.00, 
'simpel',  
602, 
0, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Himbeer-Pfirsich-Smoothie', 'https://img.chefkoch-cdn.de/rezepte/2864311438626790/bilder/942375/crop-960x640/himbeer-pfirsich-smoothie.jpg', 6);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Himbeer-Pfirsich-Smoothie', 'https://img.chefkoch-cdn.de/rezepte/2864311438626790/bilder/939248/crop-960x640/himbeer-pfirsich-smoothie.jpg', 6);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Himbeeren', 150, 'Gramm', 6);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Pfirsich', 150, 'Gramm', 6);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Vanilleschote', 0.5, 'Stück', 6);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Buttermilch', 200, 'Milliliter', 6);



-- Model for https://www.chefkoch.de/magazin/artikel/3390,0/Chefkoch/Gesunde-Rezepte-von-IN-FORM-empfohlen.html#gesunde-rezepte-f-r-das-fr-hst-ck_35944_5
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
7,
RANDOM_UUID(),
'Fitness Shake', 
'Für Fitnessbewusste und Genießer', 
'Alles im Mixer pürieren oder in ein ausreichend hohes Gefäß füllen und mit dem Stabmixer gut mixen. Kalt servieren.',
'Getränk', 
1, 
693.00, 
'simpel',  
300, 
0, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fitness Shake', 'https://img.chefkoch-cdn.de/rezepte/1456751250669956/bilder/707048/crop-960x640/fitness-shake.jpg', 7);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Fitness Shake', 'https://img.chefkoch-cdn.de/rezepte/1456751250669956/bilder/1309163/crop-960x640/fitness-shake.jpg', 7);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Joghurt ', 250, 'Gramm', 7);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Magerquark ', 50, 'Gramm', 7);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Milch ', 50, 'Milliliter', 7);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Erdbeeren', 150, 'Gramm', 7);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Banane', 1, 'Stück', 7);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Müsli oder Haferflocken', 50, 'Gramm', 7);


-- Model for https://www.chefkoch.de/rezepte/2004131324477148/Chicken-Tikka-Masala-im-europaeischen-Herbst.html
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
8,
RANDOM_UUID(),
'Chicken Tikka Masala', 
'Hähnchen mit Pilzen, Kürbis und Reis nach Tikka Masala Art', 
'Zuerst wird die Gewürzmischung hergestellt. Dazu die Senfkörner im Öl anbraten bis sie springen. Dann das Garam Masala, den Koriander, die Kardamomkapseln und Cumin dazugeben. Anschmoren, nebenbei Knoblauch und Ingwer kleinschneiden / pressen und dazugeben. Das Wasser für den Reis aufsetzen und den Basmatireis im Salzwasser garen. Dann das Hähnchenbrustfilet kleinschneiden und in der Pfanne anbraten. Den Kürbis schälen, würfeln und dazugeben. Mit etwas Pfeffer und Salz würzen. Nach dem Anbraten bei mittlerer Hitze weitergaren. Zurück zum Topf mit der Gewürzmischung: den Joghurt dazugeben und die Herdplatte ausstellen, damit der Joghurt nicht ausflockt sondern nur warm wird. Jetzt die geputzten und etwas zerkleinerten Pilze mit in die Pfanne zum Fleisch geben und kurz anschmoren. Kurz bevor der Reis gar ist, die Joghurt-Gewürzmischung in die Fleischpfanne geben. Unterrühren, den Limettensaft, das Tomatenmark, den Cayennepfeffer und die Tasse Wasser dazugeben und nach Bedarf noch etwas mit den verschiedenen Gewürzen aus der Gewürzmischung abschmecken. Es sollte jetzt den typischen Geschmack von Chicken Tikka Masala haben, mit der Ergänzung durch den leicht süßlichen Kürbis und die Pilze. Auf dem Teller anrichten und das gehackte Koriandergrün darüber geben.',
'Hauptspeise', 
1, 
700.00, 
'schwer',  
1200, 
1800, 
800);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Chicken Tikka Masala', 'https://img.chefkoch-cdn.de/rezepte/2004131324477148/bilder/1049020/crop-960x640/chicken-tikka-masala-im-europaeischen-herbst.jpg', 8);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Chicken Tikka Masala', 'https://img.chefkoch-cdn.de/rezepte/2004131324477148/bilder/1163580/crop-960x640/chicken-tikka-masala-im-europaeischen-herbst.jpg', 8);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Hähnchenbrustfilet ', 30, 'Gramm', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Joghurt ', 200, 'Gramm', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Senfkörner ', 1, 'Esslöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Sonnenblumenöl', 3, 'Esslöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Garam Masala', 2, 'Teelöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Kardamomkapsel', 3, 'Stück', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Koriander', 1, 'Teelöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Kreuzkümmel', 1, 'Teelöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Knoblauch', 1, 'Zehe', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Ingwer', 4, 'Zentimeter', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Salz', 0.5, 'Teelöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Cayennepfeffer', 1, 'Teelöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Tomatenmark', 2, 'Esslöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Limettensaft', 5, 'Esslöffel', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Austernpilze', 100, 'Gramm', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Braune Champignons', 100, 'Gramm', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Wasser', 1, 'Tasse', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Koriandergrün', 1, 'Bund', 8);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Basmati', 120, 'Gramm', 8);


-- Model for https://www.chefkoch.de/rezepte/2004131324477148/Chicken-Tikka-Masala-im-europaeischen-Herbst.html
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
9,
RANDOM_UUID(),
'Chia-Samen-Pudding mit Obst', 
'Ein gesunder Start in den Tag', 
'Am Vorabend die ersten vier Zutaten vermengen und über Nacht in den Kühlschrank stellen. Am Morgen das Obst nach Wahl schneiden und in eine Schüssel geben. Den Pudding und den Joghurt vermengen und auf das Obst geben. Nüsse nach Belieben einrühren und mit Zimt abschmecken. Der Pudding lässt sich auch mit Trockenfrüchten zubereiten.',
'Frühstück', 
1, 
500.00, 
'leicht',  
603, 
0, 
43200);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Chia-Samen-Pudding mit Obst', 'https://img.chefkoch-cdn.de/rezepte/2670501419177632/bilder/1095072/crop-960x640/chia-samen-pudding-mit-obst.jpg', 9);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Chia-Samen-Pudding mit Obst', 'https://img.chefkoch-cdn.de/rezepte/2670501419177632/bilder/1000275/crop-960x640/chia-samen-pudding-mit-obst.jpg', 9);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Chiasamen ', 1, 'Esslöffel', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Milch ', 100, 'Milliliter', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Leinsamen ', 2, 'Teelöffel', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Haferflocken', 2, 'Esslöffel', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Joghurt', 100, 'Gramm', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Obst', 1, 'Handvoll', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Nüsse', 1, 'Handvoll', 9);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zimt', 1, 'Messerspitze', 9);


-- Model for https://www.chefkoch.de/rezepte/476631141628800/Zucchini-Puffer.html
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
10,
RANDOM_UUID(),
'Zucchini - Puffer', 
'Mücver (Türkisch)', 
'Die Zucchini und die Zwiebeln fein in eine Schüssel reiben. Die übrigen Zutaten hinzufügen und zu einem dickflüssigen Teig verarbeiten. Etwas Öl in einer Pfanne erhitzen. Den Teig esslöffelweise hineingeben und flach drücken, einige Minuten auf beiden Seiten goldbraun braten. Überschüssiges Öl auf Küchenrolle abtropfen lassen und anschließend heiß oder kalt servieren.',
'Frühstück', 
1, 
605.00, 
'mittel',  
1200, 
900, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Zucchini - Puffer', 'https://img.chefkoch-cdn.de/rezepte/476631141628800/bilder/340925/crop-960x640/zucchini-puffer.jpg', 10);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Zucchini - Puffer', 'https://img.chefkoch-cdn.de/rezepte/476631141628800/bilder/784558/crop-960x640/zucchini-puffer.jpg', 10);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zucchini ', 4, 'Stück', 10);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Zwiebel ', 3, 'Stück', 10);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Eier ', 3, 'Stück', 10);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Minze', 2, 'Esslöffel', 10);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Knoblauch', 1, 'Zehe', 10);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Mehl', 150, 'Gramm', 10);


-- Model for https://www.chefkoch.de/rezepte/476631141628800/Zucchini-Puffer.html
INSERT INTO recipe (id, uuid, title, sub_title, description, category, servings, calories, level_of_difficulty, working_time_in_seconds, cooking_time_in_seconds, resting_time_in_seconds) 
VALUES (
11,
RANDOM_UUID(),
'Tagliatelle al promodoro', 
'verfeinert mit Knoblauch und Petersilie, ein einfaches und sehr schnelles Essen', 
'Die Tagliatelle bissfest abkochen und beiseite stellen!

Für die Soße:
Den Knoblauch fein hacken und zusammen mit dem Öl kurz anbraten. Die halbierten Tomaten dazugeben und ca. 2 Minuten mitbraten. Das Ganze mit der Brühe ablöschen den Frischkäse einrühren, mit den Gewürzen und der gehackten Petersilie abschmecken. Die gekochten Nudeln untermischen und nun heiß servieren! Der absolute Hit sind zu dieser Soße natürlich selbstgemachte Tagliatelle!',
'Hauptspeiße', 
1, 
354.00, 
'simpel',  
599, 
600, 
0);

INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Tagliatelle al promodoro', 'https://img.chefkoch-cdn.de/rezepte/476631141628800/bilder/340925/crop-960x640/zucchini-puffer.jpg', 11);
INSERT INTO image (uuid, image_name, image_url, recipe_id) VALUES (RANDOM_UUID(), 'Tagliatelle al promodoro', 'https://img.chefkoch-cdn.de/rezepte/476631141628800/bilder/784558/crop-960x640/zucchini-puffer.jpg', 11);


INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Nudeln, frische Tagliatelle ', 250, 'Gramm', 11);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Cocktailtomaten ', 500, 'Gramm', 11);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Knoblauch', 2, 'Zehe', 11);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Frischkäse mit Kräutern, oder Schmand', 2, 'Esslöffel', 11);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Gemüsebrühe', 250, 'Milliliter', 11);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Petersilie', 1, 'Bund', 11);
INSERT INTO ingredient (uuid, ingredient_name, ingredient_quantity, ingredient_unit, recipe_id) VALUES (RANDOM_UUID(), 'Öl', 1, 'Esslöffel', 11);