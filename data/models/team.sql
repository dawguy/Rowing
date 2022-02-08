create table team
(
	team serial,
	name varchar,
    competition_level int
    constraint team_competition_level_fk
        references competition_level
);

create unique index team_team_uindex
	on team (team);

alter table team
	add constraint team_pk
		primary key (team);

