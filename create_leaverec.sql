create table leave_rec(
    rec_id int AUTO_INCREMENT primary key,
    ask_id varchar(30),
    replace_id varchar(30),
    dpt_id varchar(6),
    rec_date date,
    time_slot varchar(20),
    replace_status int,
    admin_status int,
    msg varchar(400)
);
