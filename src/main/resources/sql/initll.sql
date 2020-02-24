INSERT IGNORE INTO `user`
VALUES (1, '2019-10-21 13:26:37.438000', 'test@test.fr', 'test', 'test',
        '$2a$10$dRfKgYSqqyb4zVm5aaqhF.tHIWzQyZORBAq/KnSgdGrB0YI/ZWpga', NULL, NULL),
       (2, '2019-10-21 13:26:37.438000', 'admin@test.fr', 'admin', 'admin',
        '$2a$10$dRfKgYSqqyb4zVm5aaqhF.tHIWzQyZORBAq/KnSgdGrB0YI/ZWpga', NULL, NULL);

INSERT IGNORE INTO `roles`
VALUES (1, 'ROLE_CUSTOMER'),
       (2, 'ROLE_ADMIN');

INSERT INTO `model` VALUES (1,_binary '^A','2020-02-24 08:06:16.745000','https://upload.wikimedia.org/wikipedia/commons/5/56/FardierdeCugnot20050111.jpg','Vehicules','cars','2020-02-24 08:06:16.745000'),
(2,_binary '^A','2020-02-24 08:06:16.840000','https://images.samsung.com/is/image/samsung/p5/fr/home/curation/S10_488x488_hp.png','Telephones','phones','2020-02-24 08:06:16.840000'),
(3,_binary '^A','2020-02-24 08:06:17.030000','https://dyw7ncnq1en5l.cloudfront.net/optim/comparison/46/46017/063dd344-quel-lave-linge-choisir__300_170.jpeg','Lave Linge','lavelinge','2020-02-24 08:06:17.030000'),
(4,_binary '^A','2020-02-24 08:06:17.232000','https://img-4.linternaute.com/858VfT_qzUeAMJUlkiZynPJ05JE=/540x/smart/f1edbe1fabd24a388a8b17fd8df2a71d/ccmcms-linternaute/10656724.jpg','Auto Mandataire','carsmand','2020-02-24 08:06:17.232000');

INSERT IGNORE INTO `model_property`
VALUES (1, _binary '', '2020-02-04 16:31:06.840000', _binary '', _binary '', _binary '', 0, 0, 'Fabricant', 'maker',
        1, '2020-02-04 16:31:06.840000'),
       (2, _binary '', '2020-02-04 16:31:06.840000', _binary '', _binary '', _binary '', 0, 0, 'Modèle', 'model', 1,
        '2020-02-04 16:31:06.840000'),
       (3, _binary '', '2020-02-04 16:31:06.840000', _binary '', _binary '', _binary '', 0, 0, 'Taille', 'size', 1,
        '2020-02-04 16:31:06.840000'),
       (4, _binary '', '2020-02-04 16:31:06.840000', _binary '\0', _binary '', _binary '', 0, 0, 'Format', 'format',
        1, '2020-02-04 16:31:06.840000'),
       (5, _binary '', '2020-02-04 16:31:06.840000', _binary '\0', _binary '', _binary '', 5, 2, 'Nombre de portes',
        'doors', 0, '2020-02-04 16:31:06.840000'),
       (6, _binary '', '2020-02-04 16:31:06.840000', _binary '\0', _binary '', _binary '', 5, 4, 'Nombre de Places',
        'seats', 0, '2020-02-04 16:31:06.840000'),
       (7, _binary '', '2020-02-04 16:31:06.840000', _binary '', _binary '', _binary '', 0, 0, 'Prix', 'price', 0,
        '2020-02-04 16:31:06.840000'),
       (8, _binary '\0', '2020-02-04 16:31:06.840000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Image', 'img',
        1, '2020-02-04 16:31:06.840000'),

       (9, _binary '', '2020-02-04 16:21:52.418000', _binary '', _binary '', _binary '', 0, 0, 'Nom', 'name', 1,
        '2020-02-04 16:21:52.418000'),
       (10, _binary '', '2020-02-04 16:21:52.418000', _binary '', _binary '', _binary '', 0, 0, 'Description',
        'description', 1, '2020-02-04 16:21:52.418000'),
       (11, _binary '', '2020-02-04 16:21:52.418000', _binary '', _binary '', _binary '', 0, 0, 'Prix', 'price', 0,
        '2020-02-04 16:21:52.418000'),
       (12, _binary '\0', '2020-02-04 16:16:40.610000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Image', 'img',
        1, '2020-02-04 16:16:40.610000'),
       (13, _binary '\0', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Lien',
        'linkto', 1, '2020-02-04 16:31:06.913000'),
       (14, _binary '', '2020-02-04 16:31:06.913000', _binary '', _binary '\0', _binary '\0', 0, 0, 'Detail',
        'infos2', 1, '2020-02-04 16:31:06.913000'),
       (15, _binary '', '2020-02-04 16:31:06.913000', _binary '', _binary '\0', _binary '\0', 0, 0, 'Detail2',
        'infos3', 1, '2020-02-04 16:31:06.913000'),
       (16, _binary '', '2020-02-04 16:31:06.913000', _binary '', _binary '\0', _binary '\0', 0, 0, 'Detail3',
        'infos4', 1, '2020-02-04 16:31:06.913000'),
       (17, _binary '', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Avis', 'rating',
        1, '2020-02-04 16:31:06.913000'),

       (18, _binary '', '2020-02-04 16:21:52.418000', _binary '', _binary '', _binary '', 0, 0, 'Nom', 'name', 1,
        '2020-02-04 16:21:52.418000'),
       (19, _binary '', '2020-02-04 16:21:52.418000', _binary '', _binary '', _binary '', 0, 0, 'Description',
        'description', 1, '2020-02-04 16:21:52.418000'),
       (20, _binary '', '2020-02-04 16:21:52.418000', _binary '', _binary '', _binary '', 0, 0, 'Prix', 'price', 0,
        '2020-02-04 16:21:52.418000'),
       (21, _binary '\0', '2020-02-04 16:16:40.610000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Image', 'img',
        1, '2020-02-04 16:16:40.610000'),
       (22, _binary '\0', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Lien',
        'linkto', 1, '2020-02-04 16:31:06.913000'),
       (23, _binary '', '2020-02-04 16:31:06.913000', _binary '', _binary '\0', _binary '\0', 0, 0, 'Detail',
        'infos2', 1, '2020-02-04 16:31:06.913000'),
       (24, _binary '', '2020-02-04 16:31:06.913000', _binary '', _binary '\0', _binary '\0', 0, 0, 'Detail2',
        'infos3', 1, '2020-02-04 16:31:06.913000'),
       (25, _binary '', '2020-02-04 16:31:06.913000', _binary '', _binary '\0', _binary '\0', 0, 0, 'Detail3',
        'infos4', 1, '2020-02-04 16:31:06.913000'),
       (26, _binary '', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Avis', 'rating',
        1, '2020-02-04 16:31:06.913000'),
       (27, _binary '', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Promotion',
        'promo', 1, '2020-02-04 16:31:06.913000'),
       (28, _binary '', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Prix initial','prix_barre', 1, '2020-02-04 16:31:06.913000'),
       (29, _binary '', '2020-02-04 16:31:06.913000', _binary '\0', _binary '\0', _binary '\0', 0, 0, 'Pourcentage','striped_price', 1, '2020-02-04 16:31:06.913000'),

        (30,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '^A',_binary '^A',0,0,'Nom','name',1,'2020-02-24 08:06:17.232000'),
        (31,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '^A',_binary '^A',0,0,'Description','description',1,'2020-02-24 08:06:17.232000'),
        (32,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '^A',_binary '^A',0,0,'Prix','price',0,'2020-02-24 08:06:17.232000'),
        (33,_binary '\0','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Image','imgUrl',1,'2020-02-24 08:06:17.232000'),
        (34,_binary '\0','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Lien','linkto',1,'2020-02-24 08:06:17.232000'),
        (35,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '^A',_binary '^A',0,0,'Marque','maker',1,'2020-02-24 08:06:17.232000'),
        (36,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '^A',_binary '^A',0,0,'Modele','model',1,'2020-02-24 08:06:17.232000'),
        (37,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '^A',_binary '\0',0,0,'Puissance','puissance',0,'2020-02-24 08:06:17.232000'),
        (38,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '\0',_binary '\0',0,0,'Boite vitesse type','bvtype',1,'2020-02-24 08:06:17.232000'),
        (39,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Pourcentage remise','percentage',1,'2020-02-24 08:06:17.232000'),
        (40,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Prix loa','loaprice',1,'2020-02-24 08:06:17.232000'),
        (41,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Prix barré','prixbarre',0,'2020-02-24 08:06:17.232000'),
        (42,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '\0',_binary '^A',0,0,'Carburant','carburant',1,'2020-02-24 08:06:17.232000'),
        (43,_binary '^A','2020-02-24 08:06:17.232000',_binary '^A',_binary '\0',_binary '\0',0,0,'Nbre de places','nbPlaces',1,'2020-02-24 08:06:17.232000'),
        (44,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Boite Vitesse detail','bvdetail',1,'2020-02-24 08:06:17.232000'),
        (45,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Details Carrosserie','detail1',1,'2020-02-24 08:06:17.232000'),
        (46,_binary '^A','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Detail Puissance','detail2',1,'2020-02-24 08:06:17.232000'),
        (47,_binary '\0','2020-02-24 08:06:17.232000',_binary '\0',_binary '\0',_binary '\0',0,0,'Disponibilité','datedispo',1,'2020-02-24 08:06:17.232000');;

INSERT IGNORE INTO `model_model_properties`
VALUES (1, 1),       (1, 2),       (1, 3),       (1, 4),       (1, 5),       (1, 6),       (1, 7),       (1, 8),
       (2, 9),       (2, 10),       (2, 11),       (2, 12),       (2, 13),       (2, 14),       (2, 15),       (2, 16),       (2, 17),
       (3, 18),       (3, 19),       (3, 20),       (3, 21),       (3, 22),       (3, 23),       (3, 24),       (3, 25),       (3, 26),       (3, 27),       (3, 28),       (3, 29),
       (4,30),(4,31),(4,32),(4,33),(4,34),(4,35),(4,36),(4,37),(4,38),(4,39),(4,40),(4,41),(4,42),(4,43),(4,44),(4,45),(4,46),(4,47);

INSERT IGNORE INTO `website_configuration`
VALUES (1, '#c26d6d', '#50459c', '#82bd2a', '2020-02-04 16:31:07.062000', _binary '', _binary '', _binary '', _binary '', _binary '',
        _binary '',
        'https://img.freepik.com/vecteurs-libre/vs-contre-texte-logo-pour-jeu-bataille-combat-symbole-dessin-anime-plat_101884-738.jpg?size=626&ext=jpg',
        'ComparIt', '2020-02-24 16:31:07.062000');

INSERT IGNORE INTO `website_configuration_models`
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);

