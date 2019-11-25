insert into compareit.website_configuration(color1,color2,color3,feat_analytic,logo) VALUES ('blue','white','red',1,'logo.svg');
insert into compareit.company(adress,presentation,provider_email,provider_name,site,telephone) VALUES ('test','testouille','a@gmail.com','test','oui','0606060606');
insert into compareit.`user` (email,first_name,last_name,type_user,company_id) VALUES ('a.b@gmail.com','a','b',1,1);
insert into compareit.company_members values (1,1);
insert into compareit.`filter`(alert_type,is_alert,is_email,users_id) VALUES (1,1,1,1);
insert into compareit.user_filters VALUES(1,1);
insert into compareit.model (activated,name,technical_name) values (1,'test','test');
insert into compareit.model_property(filtrable,filtrable_advanced,mandatory,name,technical_name,`type`,model_id)
VALUES(1,1,1,'test','testi',1,1);
insert into compareit.model_model_properties values (1,1);
insert into compareit.alert(is_alerte_mail,is_consulted,filter_id) VALUES (1,1,1);
insert into compareit.filter_alerts values (1,1);
insert into compareit.filter_criterias(filter_id,criterias_key,criterias) values (1,1,"test");
Insert into compareit.alert_list_products_id(alert_id,list_products_id) values (1,'{message:test}');






