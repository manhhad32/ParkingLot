if [ "$1" == "file_inputs.txt" ] || [ "$#" == "0" ]
then
 java -jar bin/parking_lot/parking-lot.jar file_inputs.txt
 else
  # shellcheck disable=SC2068
  java -jar bin/parking_lot/parking-lot.jar $@
fi
