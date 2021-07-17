create table school
(
	school serial,
	name varchar,
    competition_level int
    constraint school_competition_level_fk
        references competition_level
);

create unique index school_school_uindex
	on school (school);

alter table school
	add constraint school_pk
		primary key (school);

