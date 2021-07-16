create table water_workout
(
	water_workout serial,
	date date,
	boat int not null
		constraint water_workout_boat_fk
			references boat
);

create unique index water_workout_water_workout_uindex
	on water_workout (water_workout);

alter table water_workout
	add constraint water_workout_pk
		primary key (water_workout);


