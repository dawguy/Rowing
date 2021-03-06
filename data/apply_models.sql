-- Type tables first as they will never have any dependencies to manage
\i models/competition_level.sql
\i models/shell_type.sql

-- Top levels which only rely on type tables or nothing
\i models/athlete.sql
\i models/team.sql

--
-- Some dependencies and order matters below this point
--

-- Requires athlete
\i models/shell.sql
\i models/boat.sql

-- Requires team
\i models/template_workout.sql

-- Requires template workout team team
\i models/assigned_workout.sql
\i models/template_split.sql

-- Requires athlete and assigned workout
\i models/erg_workout.sql
\i models/erg_split.sql

-- Requires boat and assigned workout
\i models/water_workout.sql
\i models/water_split.sql

-- Requires boat, water_workout and athlete
\i models/water_workout_athlete_split.sql
