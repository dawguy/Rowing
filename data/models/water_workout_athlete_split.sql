create table water_workout_athlete_split
(
	water_workout_athlete_split serial not null,
	water_split int not null
		constraint water_workout_athlete_split_water_split_fk
			references water_split,
	athlete int not null
		constraint water_workout_athlete_split_athlete_fk
			references athlete,
    heart_rate int,
    power int
);

create unique index water_workout_athlete_split_uindex
	on water_workout_athlete_split (water_workout_athlete_split);

alter table water_workout_athlete_split
	add constraint water_workout_athlete_split_pk
		primary key (water_workout_athlete_split);

