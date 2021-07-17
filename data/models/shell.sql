create table shell
(
	shell serial not null,
	name varchar,
    shell_type int
        constraint shell_shell_type_fk
            references shell_type,
	school int
		constraint shell_school_fk
			references school
);

create unique index shell_shell_uindex
	on shell (shell);

alter table shell
	add constraint shell_pk
		primary key (shell);
