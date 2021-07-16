create table erg_split
(
	erg_split serial not null,
	erg_workout int not null
		constraint erg_split_workout_fk
			references erg_workout,
	seq int not null,
	duration timestamp,
	distance int,
	heart_rate int,
	power int
);

comment on column erg_split.seq is 'The ordering of the split in the overall erg workout';

create unique index erg_split_erg_split_uindex
	on erg_split (erg_split);

alter table erg_split
	add constraint erg_split_pk
		primary key (erg_split);

