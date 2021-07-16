create table boat
(
	boat serial not null,
    shell int
        constraint boat_shell_fk
            references shell,
	coxswain int
        constraint boat_coxswain_fk
			references athlete,
	seat_8 int
		constraint boat_seat_8_fk
			references athlete,
	seat_7 int
		constraint boat_seat_7_fk
			references athlete,
	seat_6 int
		constraint boat_seat_6_fk
			references athlete,
	seat_5 int
		constraint boat_seat_5_fk
			references athlete,
	seat_4 int
		constraint boat_seat_4_fk
			references athlete,
	seat_3 int
		constraint boat_seat_3_fk
			references athlete,
	seat_2 int
		constraint boat_seat_2_fk
			references athlete,
	seat_1 int
		constraint boat_seat_1_fk
			references athlete
);

create unique index boat_boat_uindex
	on boat (boat);

alter table boat
	add constraint boat_pk
		primary key (boat);
