binFolder="bin"
file="bin/parking_lot/parking-lot.jar"
classes="bin/classes"
jarFolder=bin/parking_lot
mkdir $binFolder
mkdir $jarFolder

fileInput=file_inputs.txt

if [ -f "$file" ]
then
  echo 'deleting files jar...'
  rm -rf $file
fi
if [ -f "$fileInput" ]
then
  echo 'clean file input ...'
  rm -rf $jarFolder/$fileInput
fi
if [ -d "$classes" ]
then
  echo 'deleting files *.class ...'
  rm -rf $classes
fi
javac -target 11 -source 11 -d bin/classes src/parking/*.java src/parking/process/*.java

echo 'Manifest-Version: 1.0' > $jarFolder/MANIFEST.txt
echo 'Main-Class: parking.ApplicationMain' >> $jarFolder/MANIFEST.txt

jar -cvfm $jarFolder/parking-lot.jar $jarFolder/MANIFEST.txt -C bin/classes .
echo '*****build successfully!******'
rm -rf $jarFolder/MANIFEST.txt
rm -rf bin/classes

#create file inputs.txt
echo 'create_parking_lot 6' > $jarFolder/$fileInput
echo 'park KA-01-HH-1234 White' >> $jarFolder/$fileInput
echo 'park KA-01-HH-9999 White' >> $jarFolder/$fileInput
echo 'park KA-01-BB-0001 Black' >> $jarFolder/$fileInput
echo 'park KA-01-HH-7777 Red' >> $jarFolder/$fileInput
echo 'park KA-01-HH-2701 Blue' >> $jarFolder/$fileInput
echo 'park KA-01-HH-3141 Black' >> $jarFolder/$fileInput
echo 'leave 4' >> $jarFolder/$fileInput
echo 'status' >> $jarFolder/$fileInput
echo 'park KA-01-P-333 White' >> $jarFolder/$fileInput
echo 'park DL-12-AA-9999 White' >> $jarFolder/$fileInput
echo 'registration_numbers_for_cars_with_colour White' >> $jarFolder/$fileInput
echo 'slot_numbers_for_cars_with_colour White' >> $jarFolder/$fileInput
echo 'slot_number_for_registration_number KA-01-HH-3141' >> $jarFolder/$fileInput
echo 'slot_number_for_registration_number MH-04-AY-1111' >> $jarFolder/$fileInput