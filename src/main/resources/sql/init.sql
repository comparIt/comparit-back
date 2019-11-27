REPLACE INTO compareIt.`user`
(id, createdat, email, first_name, last_name, password, updatedat, company_id)
VALUES(1, '2019-10-21 13:26:37.438', 'test@test.fr', 'test', 'test', '$2a$10$dRfKgYSqqyb4zVm5aaqhF.tHIWzQyZORBAq/KnSgdGrB0YI/ZWpga', NULL, NULL);

REPLACE INTO compareIt.roles
(user_id, roles)
VALUES(1, 'ROLE_CUSTOMER');

INSERT INTO `model` VALUES (1,_binary '','2019-11-26 18:05:38.036000','cars','cars','2019-11-26 18:05:38.036000'),(2,_binary '','2019-11-26 21:10:33.244000','Mobiles','phones','2019-11-26 21:10:33.244000');

INSERT INTO `model_property` VALUES (1,_binary '','2019-11-26 18:05:37.653000',_binary '',_binary '\0',_binary '','Fabricant','maker',1,'2019-11-26 18:05:37.655000'),(2,_binary '','2019-11-26 18:05:37.940000',_binary '',_binary '\0',_binary '','Modèle','model',1,'2019-11-26 18:05:37.940000'),(3,_binary '','2019-11-26 18:05:37.966000',_binary '',_binary '',_binary '','Taille','size',1,'2019-11-26 18:05:37.966000'),(4,_binary '\0','2019-11-26 18:05:37.984000',_binary '\0',_binary '',_binary '','Format','format',1,'2019-11-26 18:05:37.984000'),(5,_binary '\0','2019-11-26 18:05:38.001000',_binary '\0',_binary '',_binary '','Nombre de portes','doors',0,'2019-11-26 18:05:38.001000'),(6,_binary '','2019-11-26 18:05:38.019000',_binary '\0',_binary '',_binary '','Nombre de Places','seats',0,'2019-11-26 18:05:38.019000'),(7,_binary '','2019-11-26 21:10:33.145000',_binary '',_binary '',_binary '','Marque','brand',1,'2019-11-26 21:10:33.146000'),(8,_binary '','2019-11-26 21:10:33.228000',_binary '',_binary '',_binary '','Modele','model',1,'2019-11-26 21:10:33.228000');

INSERT INTO `model_model_properties` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,7),(2,8);

INSERT INTO `website_configuration` VALUES (1,'#c26d6d','#50459c','#82bd2a','2019-11-26 18:05:38.103000',_binary '','picture','2019-11-26 18:05:38.103000');

INSERT INTO `website_configuration_models` VALUES (1,1),(1,2);

