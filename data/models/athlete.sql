create table athlete
(
	athlete serial,
	name varchar,
	birthday date
);

create unique index athlete_athlete_uindex
	on athlete (athlete);

alter table athlete
	add constraint athlete_pk
		primary key (athlete);
