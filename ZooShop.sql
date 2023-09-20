update user_role set roles='ADMIN' where user_id=1

select * from client;
select * from type_product;
select * from store;
select * from product;
select * from licence;

insert into bed(number_licence) VALUES
(125),
(345),
(375),
(567);

insert into palat(number_palat,bed_id)VALUES
(11,1),
(22, 2),
(33, 3);
