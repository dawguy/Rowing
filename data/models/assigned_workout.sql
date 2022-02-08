create table assigned_workout
(
	assigned_workout serial,
	date date,
    name varchar,
	team int
		constraint template_workout_team_fk
			references team,
	template_workout int not null
		constraint assigned_workout_template_workout_fk
			references template_workout
);

create unique index assigned_workout_assigned_workout_uindex
	on assigned_workout (assigned_workout);

alter table assigned_workout
	add constraint assigned_workout_pk
		primary key (assigned_workout);
