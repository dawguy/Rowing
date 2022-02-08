create table erg_workout
(
	erg_workout serial,
	date date,
	athlete int not null
		constraint erg_workout_athlete_fk
			references athlete,
	assigned_workout int
		constraint water_workout_assigned_workout_fk
			references assigned_workout
);

create unique index erg_workout_erg_workout_uindex
	on erg_workout (erg_workout);

alter table erg_workout
	add constraint erg_workout_pk
		primary key (erg_workout);


