done = FALSE;
WHILE ! done DO
	dir = DIRECTION_RANDOM;
	done = (IS_VALID dir) & (! (IS_OCCUPIED dir));
ENDLOOP
MOVE_DIRECTION = dir;