x = 1;
//this is a line comment
LOOP 10 DO
    x++;
    IF (x % 2) == 0 THEN
        println x
    ENDIF
ENDLOOP /* this also a comment*/
println /* more comment*/ x;
println PI;
/* a comment 
spanning
multiple lines */ 

WHILE x <= 20 DO
    x++;
    println 42;
    LOOP 2 DO
        println 1;
    ENDLOOP
ENDLOOP

println 80;