//this robot will iterate over all adjacent cells and
//take one that contains food
//if none contain food is goes random

//pick valid random direction as defaul
done = FALSE;
WHILE ! done DO
	dir = DIRECTION_RANDOM;
	done = (IS_VALID dir) & (! (IS_OCCUPIED dir));
ENDLOOP
MOVE_DIRECTION = dir;

//if there is food in the neighborhood go there instead
done = FALSE;
counter = 0;
LOOP 8 DO
	dir = counter;
	counter++;
	done = HAS_FOOD dir;
	IF done THEN
		MOVE_DIRECTION = dir;
	ENDIF
ENDLOOP