//this robot will iterate over all adjacent cells and
//take one that contains food
//if none contain food is goes random
done = FALSE;
counter = 0;
MOVE_DIRECTION = DIRECTION_STAY;
LOOP 8 DO
	dir = counter;
	counter++;
	done = HAS_FOOD dir;
	IF done THEN
		MOVE_DIRECTION = dir;
	ENDIF
ENDLOOP
