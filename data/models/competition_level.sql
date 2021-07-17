create table competition_level
(
	competition_level serial not null,
	level int not null
);

create unique index competition_level_competition_level_uindex
	on competition_level (competition_level);

alter table competition_level
	add constraint competition_level_pk
		primary key (competition_level);

