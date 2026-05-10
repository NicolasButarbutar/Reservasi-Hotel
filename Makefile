compile:
	javac -d bin hotelreservation/Main.java hotelreservation/model/*.java hotelreservation/notification/*.java hotelreservation/payment/*.java hotelreservation/repository/*.java hotelreservation/service/*.java

run:
	java -cp bin hotelreservation.Main