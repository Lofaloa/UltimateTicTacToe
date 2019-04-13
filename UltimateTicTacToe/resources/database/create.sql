-- Represents an user that once played the game.
create table USER (
    pseudonym varchar(30) primary key,
	nvictories int not null,
	nexaequos int not null,
	ndefeats int not null,
);

-- The number of victories should be positive
ALTER TABLE USER
ADD CONSTRAINT checkNVictoriesPositive
CHECK (nvictories >= 0);

-- The number of ex aequos should be positive
ALTER TABLE USER
ADD CONSTRAINT checkNExaequosPositive
CHECK (nexaequos >= 0);

-- The number of defeats should be positive
ALTER TABLE USER
ADD CONSTRAINT checkNDefeatsPositive
CHECK (ndefeats >= 0);