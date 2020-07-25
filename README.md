#Setup environment:

System requirement:
install java 11
link to download: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

#build project:
build to create file: bin/parking_lot/parking-lot.jar
and create test scenario in file bin/parking_lot/file_input.txt
can change test scenario by the way change param content in this file.
use cmd: ./make_build.sh 

#run to test:
1.for run test scenario in file bin/parking_lot/file_inputs.txt
use ./make_run.sh
or ./make_run.sh file_input.txt

to exit
input text exit
can run:
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111
