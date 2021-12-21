-- Type tables first as they will never have any dependencies to manage
\i models/competition_level.sql
\i models/shell_type.sql

-- Top levels which only rely on type tables or nothing
\i models/athlete.sql
\i models/school.sql

--
-- Some dependencies and order matters below this point
--
\i models/shell.sql
\i models/boat.sql

-- Requires athlete
\i models/erg_workout.sql
\i models/erg_split.sql

-- Requires boat
\i models/water_workout.sql
\i models/water_split.sql

-- Requires boat and athlete
\i models/water_workout_athlete_split.sql
