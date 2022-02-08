create table template_workout
(
	template_workout serial,
    name varchar,
	team int
		constraint template_workout_team_fk
			references team
);

create unique index template_workout_template_workout_uindex
	on template_workout (template_workout);

alter table template_workout
	add constraint template_workout_pk
		primary key (template_workout);
