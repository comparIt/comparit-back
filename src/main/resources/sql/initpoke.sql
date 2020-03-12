INSERT IGNORE INTO `user` VALUES (1,'2019-10-21 13:26:37.438000','test@test.fr','test','test','$2a$10$dRfKgYSqqyb4zVm5aaqhF.tHIWzQyZORBAq/KnSgdGrB0YI/ZWpga',NULL,NULL),(2,'2019-10-21 13:26:37.438000','admin@test.fr','admin','admin','$2a$10$dRfKgYSqqyb4zVm5aaqhF.tHIWzQyZORBAq/KnSgdGrB0YI/ZWpga',NULL,NULL),(3,'2020-03-09 21:06:17.284000','Msansen@gmail.com','Mathieu','SANSEN','$2a$10$zfYZsjWvZduDtE5faEkpNuzbZV0HeJu1SgYSgw3uRaA5uuiIyZrpa',NULL,NULL);

INSERT IGNORE INTO `roles`
VALUES (1, 'ROLE_CUSTOMER'),
       (2, 'ROLE_ADMIN');

INSERT IGNORE INTO `model` VALUES (1,0,'2020-03-11 21:10:08.780000','https://upload.wikimedia.org/wikipedia/commons/5/56/FardierdeCugnot20050111.jpg','Vehicules','cars','2020-03-11 21:10:08.780000'),(2,1,'2020-03-11 21:10:08.910000','https://im.qccdn.fr/node/guide-d-achat-smartphones-4203/principal-44351.jpg','Telephones','phones','2020-03-11 21:10:08.910000'),(3,1,'2020-03-11 21:10:09.063000','https://dyw7ncnq1en5l.cloudfront.net/optim/comparison/46/46017/063dd344-quel-lave-linge-choisir__300_170.jpeg','Lave Linge','lavelinge','2020-03-11 21:10:09.063000'),(4,1,'2020-03-11 21:10:09.247000','https://img-4.linternaute.com/858VfT_qzUeAMJUlkiZynPJ05JE=/540x/smart/f1edbe1fabd24a388a8b17fd8df2a71d/ccmcms-linternaute/10656724.jpg','Auto Mandataire','carsmand','2020-03-11 21:10:09.247000'),(5,0,'2020-03-11 21:10:09.407000','https://www.cdiscount.com/pdt2/9/b/2/1/300x300/ocealed32319b2/rw/oceanic-tv-led-hd-32-80-cm-resolution-1366-x.jpg','Television','tv','2020-03-11 21:10:09.407000'),(6,1,'2020-03-11 21:10:09.499000','https://raw.githubusercontent.com/fanzeyi/pokemon.json/master/images/001.png','Pokemon','pokemon','2020-03-11 21:10:09.499000');

INSERT IGNORE INTO `model_property` VALUES (1,_binary '^A','2020-03-11 21:10:08.779000',_binary '^A',_binary '^A',_binary '^A',0,0,'Fabricant','maker',1,'2020-03-11 21:10:08.779000'),(2,_binary '^A','2020-03-11 21:10:08.779000',_binary '^A',_binary '^A',_binary '^A',0,0,'ModÃ¨le','model',1,'2020-03-11 21:10:08.779000'),(3,_binary '^A','2020-03-11 21:10:08.779000',_binary '^A',_binary '^A',_binary '^A',0,0,'Taille','size',1,'2020-03-11 21:10:08.779000'),(4,_binary '^A','2020-03-11 21:10:08.780000',_binary '\0',_binary '^A',_binary '^A',0,0,'Format','format',1,'2020-03-11 21:10:08.780000'),(5,_binary '^A','2020-03-11 21:10:08.780000',_binary '\0',_binary '^A',_binary '^A',5,2,'Nombre de portes','doors',0,'2020-03-11 21:10:08.780000'),(6,_binary '^A','2020-03-11 21:10:08.780000',_binary '\0',_binary '^A',_binary '^A',5,4,'Nombre de Places','seats',0,'2020-03-11 21:10:08.780000'),(7,_binary '^A','2020-03-11 21:10:08.780000',_binary '^A',_binary '^A',_binary '^A',0,0,'Prix','price',0,'2020-03-11 21:10:08.780000'),(8,_binary '\0','2020-03-11 21:10:08.780000',_binary '\0',_binary '\0',_binary '\0',0,0,'Image','img',1,'2020-03-11 21:10:08.780000'),(9,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '^A',_binary '^A',0,0,'Nom','name',1,'2020-03-11 21:10:08.910000'),(10,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '^A',_binary '^A',0,0,'Description','description',1,'2020-03-11 21:10:08.910000'),(11,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '^A',_binary '^A',1500,249,'Prix','price',0,'2020-03-11 21:10:08.910000'),(12,_binary '\0','2020-03-11 21:10:08.910000',_binary '\0',_binary '\0',_binary '\0',0,0,'Image','img',1,'2020-03-11 21:10:08.910000'),(13,_binary '\0','2020-03-11 21:10:08.910000',_binary '\0',_binary '\0',_binary '\0',0,0,'Lien','linkto',1,'2020-03-11 21:10:08.910000'),(14,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '\0',_binary '\0',0,0,'Marque','marque',1,'2020-03-11 21:10:08.910000'),(15,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '\0',_binary '\0',0,0,'Stockage','stockage',1,'2020-03-11 21:10:08.910000'),(16,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '\0',_binary '\0',0,0,'Ecran','ecran',1,'2020-03-11 21:10:08.910000'),(17,_binary '^A','2020-03-11 21:10:08.910000',_binary '^A',_binary '\0',_binary '\0',0,0,'OS','os',1,'2020-03-11 21:10:08.910000'),(18,_binary '^A','2020-03-11 21:10:09.063000',_binary '^A',_binary '^A',_binary '^A',0,0,'Nom','name',1,'2020-03-11 21:10:09.063000'),(19,_binary '^A','2020-03-11 21:10:09.063000',_binary '^A',_binary '^A',_binary '^A',0,0,'Description','description',1,'2020-03-11 21:10:09.063000'),(20,_binary '^A','2020-03-11 21:10:09.063000',_binary '^A',_binary '^A',_binary '^A',749,183,'Prix','price',0,'2020-03-11 21:10:09.063000'),(21,_binary '\0','2020-03-11 21:10:09.063000',_binary '\0',_binary '\0',_binary '\0',0,0,'Image','img',1,'2020-03-11 21:10:09.063000'),(22,_binary '\0','2020-03-11 21:10:09.063000',_binary '\0',_binary '\0',_binary '\0',0,0,'Lien','linkto',1,'2020-03-11 21:10:09.063000'),(23,_binary '^A','2020-03-11 21:10:09.063000',_binary '^A',_binary '\0',_binary '\0',0,0,'Detail','infos2',1,'2020-03-11 21:10:09.063000'),(24,_binary '^A','2020-03-11 21:10:09.063000',_binary '^A',_binary '\0',_binary '\0',0,0,'Detail2','infos3',1,'2020-03-11 21:10:09.063000'),(25,_binary '^A','2020-03-11 21:10:09.063000',_binary '^A',_binary '\0',_binary '\0',0,0,'Detail3','infos4',1,'2020-03-11 21:10:09.063000'),(26,_binary '^A','2020-03-11 21:10:09.063000',_binary '\0',_binary '\0',_binary '\0',0,0,'Avis','rating',1,'2020-03-11 21:10:09.063000'),(27,_binary '^A','2020-03-11 21:10:09.063000',_binary '\0',_binary '\0',_binary '\0',0,0,'Promotion','promo',1,'2020-03-11 21:10:09.063000'),(28,_binary '^A','2020-03-11 21:10:09.063000',_binary '\0',_binary '\0',_binary '\0',0,0,'Prix initial','prix_barre',1,'2020-03-11 21:10:09.063000'),(29,_binary '^A','2020-03-11 21:10:09.063000',_binary '\0',_binary '\0',_binary '\0',0,0,'Pourcentage','striped_price',1,'2020-03-11 21:10:09.063000'),(30,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '^A',_binary '^A',0,0,'Nom','name',1,'2020-03-11 21:10:09.247000'),(31,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '^A',_binary '^A',0,0,'Description','description',1,'2020-03-11 21:10:09.247000'),(32,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '^A',_binary '^A',38229,14375,'Prix','price',0,'2020-03-11 21:10:09.247000'),(33,_binary '\0','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Image','img',1,'2020-03-11 21:10:09.247000'),(34,_binary '\0','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Lien','linkto',1,'2020-03-11 21:10:09.247000'),(35,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '^A',_binary '^A',0,0,'Marque','maker',1,'2020-03-11 21:10:09.247000'),(36,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '^A',_binary '^A',0,0,'Modele','model',1,'2020-03-11 21:10:09.247000'),(37,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '^A',_binary '\0',180,75,'Puissance','puissance',0,'2020-03-11 21:10:09.247000'),(38,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '\0',_binary '\0',0,0,'Boite vitesse type','bvtype',1,'2020-03-11 21:10:09.247000'),(39,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Pourcentage remise','percentage',1,'2020-03-11 21:10:09.247000'),(40,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Prix loa','loaprice',1,'2020-03-11 21:10:09.247000'),(41,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Prix barrÃ©','prixbarre',0,'2020-03-11 21:10:09.247000'),(42,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '\0',_binary '^A',0,0,'Carburant','carburant',1,'2020-03-11 21:10:09.247000'),(43,_binary '^A','2020-03-11 21:10:09.247000',_binary '^A',_binary '\0',_binary '\0',0,0,'Nbre de places','nbPlaces',1,'2020-03-11 21:10:09.247000'),(44,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Boite Vitesse detail','bvdetail',1,'2020-03-11 21:10:09.247000'),(45,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Details Carrosserie','detail1',1,'2020-03-11 21:10:09.247000'),(46,_binary '^A','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'Detail Puissance','detail2',1,'2020-03-11 21:10:09.247000'),(47,_binary '\0','2020-03-11 21:10:09.247000',_binary '\0',_binary '\0',_binary '\0',0,0,'DisponibilitÃ©','datedispo',1,'2020-03-11 21:10:09.247000'),(48,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '^A',_binary '^A',0,0,'Nom','name',1,'2020-03-11 21:10:09.407000'),(49,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '^A',_binary '^A',0,0,'Description','description',1,'2020-03-11 21:10:09.407000'),(50,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '^A',_binary '^A',0,0,'Prix','price',0,'2020-03-11 21:10:09.407000'),(51,_binary '\0','2020-03-11 21:10:09.407000',_binary '\0',_binary '\0',_binary '\0',0,0,'Image','img',1,'2020-03-11 21:10:09.407000'),(52,_binary '\0','2020-03-11 21:10:09.407000',_binary '\0',_binary '\0',_binary '\0',0,0,'lien','linkto',1,'2020-03-11 21:10:09.407000'),(53,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'catÃ©gorie','descSub',1,'2020-03-11 21:10:09.407000'),(54,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'Marque','marque',1,'2020-03-11 21:10:09.407000'),(55,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'Frequence','frequence',1,'2020-03-11 21:10:09.407000'),(56,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'Taille','taille',1,'2020-03-11 21:10:09.407000'),(57,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'Diagonale','diagonale',1,'2020-03-11 21:10:09.407000'),(58,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'Definition','definition',1,'2020-03-11 21:10:09.407000'),(59,_binary '^A','2020-03-11 21:10:09.407000',_binary '^A',_binary '\0',_binary '\0',0,0,'Dalle','dalle',NULL,'2020-03-11 21:10:09.407000'),(60,_binary '^A','2020-03-11 21:10:09.499000',_binary '^A',_binary '^A',_binary '^A',0,0,'Experience','baseExperience',0,'2020-03-11 21:10:09.499000'),(61,_binary '^A','2020-03-11 21:10:09.499000',_binary '^A',_binary '^A',_binary '^A',0,0,'Taille','height',0,'2020-03-11 21:10:09.499000'),(62,_binary '^A','2020-03-11 21:10:09.499000',_binary '^A',_binary '^A',_binary '^A',0,0,'Nom','name',1,'2020-03-11 21:10:09.499000'),(63,_binary '^A','2020-03-11 21:10:09.499000',_binary '^A',_binary '\0',_binary '\0',0,0,'Poids','weight',1,'2020-03-11 21:10:09.499000'),(64,_binary '\0','2020-03-11 21:10:09.499000',_binary '\0',_binary '\0',_binary '\0',0,0,'image','img',1,'2020-03-11 21:10:09.499000'),(65,_binary '^A','2020-03-11 21:10:09.499000',_binary '\0',_binary '\0',_binary '\0',0,0,'Prix','price',0,'2020-03-11 21:10:09.499000'),(66,_binary '^A','2020-03-11 21:10:09.499000',_binary '^A',_binary '\0',_binary '\0',0,0,'Description','description',1,'2020-03-11 21:10:09.499000');

INSERT IGNORE INTO `model_model_properties` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(3,18),(3,19),(3,20),(3,21),(3,22),(3,23),(3,24),(3,25),(3,26),(3,27),(3,28),(3,29),(4,30),(4,31),(4,32),(4,33),(4,34),(4,35),(4,36),(4,37),(4,38),(4,39),(4,40),(4,41),(4,42),(4,43),(4,44),(4,45),(4,46),(4,47),(5,48),(5,49),(5,50),(5,51),(5,52),(5,53),(5,54),(5,55),(5,56),(5,57),(5,58),(5,59),(6,60),(6,61),(6,62),(6,63),(6,64),(6,65),(6,66);

INSERT IGNORE INTO `website_configuration` VALUES (1,'#c26d6d','#50459c','#82bd2a','2020-03-11 21:10:09.751000',_binary '^A',_binary '^A',_binary '^A',_binary '^A',_binary '^A',_binary '^A',_binary '^A','https://img.freepik.com/vecteurs-libre/vs-contre-texte-logo-pour-jeu-bataille-combat-symbole-dessin-anime-plat_101884-738.jpg?size=626&ext=jpg','contact@comparit.fr','ComparIt',NULL);

INSERT IGNORE INTO `website_configuration_models` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6);
