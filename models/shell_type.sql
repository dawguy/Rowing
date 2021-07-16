create table shell_type
(
	shell_type serial not null,
	size int,
	sweep bool,
	has_coxswain bool
);

create unique index shell_type_shell_type_uindex
	on shell_type (shell_type);

alter table shell_type
	add constraint shell_type_pk
		primary key (shell_type);

