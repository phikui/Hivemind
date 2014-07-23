x = 1;
//this is a line comment
LOOP 10 DO
    x++;
    IF (x % 2) == 0 THEN
        print x
    ENDIF
ENDLOOP /* this also a comment*/
print /* more comment*/ x;
print PI;
/* a comment 
spanning
multiple lines */ 

WHILE x <= 20 DO
    x++;
    print 42;
    LOOP 2 DO
        print 1;
    ENDLOOP
ENDLOOP

print 80;