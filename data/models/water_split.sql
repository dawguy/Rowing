create table water_split
(
	water_split serial not null,
	water_workout int not null
		constraint water_split_workout_fk
			references water_workout,
	seq int not null,
	duration timestamp,
	distance int,
    with_flow bool,
    flow_rate int
);

comment on column water_split.seq is 'The ordering of the split in the overall water workout';

create unique index water_split_water_split_uindex
	on water_split (water_split);

alter table water_split
	add constraint water_split_pk
		primary key (water_split);

