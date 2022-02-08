create table template_split
(
	template_split serial not null,
	template_workout int not null
		constraint template_split_workout_fk
			references template_workout,
	seq int not null,
	duration timestamp,
	distance int
);

comment on column template_split.seq is 'The ordering of the split in the overall template workout';

create unique index template_split_template_split_uindex
	on template_split (template_split);

alter table template_split
	add constraint template_split_pk
		primary key (template_split);
