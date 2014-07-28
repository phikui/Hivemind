//this robot will iterate over all adjacent cells and
//take one that contains food
//if none contain food is goes random
done = FALSE;
counter = 0;
LOOP 9 DO
	dir = counter;
	counter++;
	done = (IS_VALID dir) & (! (IS_OCCUPIED dir)) & (HAS_FOOD dir);
	IF done THEN
		MOVE_DIRECTION = dir;
	ENDIF
ENDLOOP
IF ! done THEN
	MOVE_DIRECTION = DIRECTION_RANDOM;
ENDIF