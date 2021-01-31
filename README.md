# D-KARS Parking Solutions
Cdac project

With increase in vehicles we are having bigger problem to park them. For any location we have to either park them miles behind or wait several hours and confused of legel parking spots.
We are trying to address this problem with state of start parking solutions. We provide authorised parking spots for multiple cities.
Customer can make decision based on proximity, pay/hour.  We have implemented microservice architecture where most services are in java spring jpa and frontend is in angular.

Project deals with 2 modules- customer and admin. In this automated system, customers can book parking spots based on the location and availability. Upon selection, an OTP through e-mail will be sent to customer, which can be entered at the time of parking. Charges are calculated from the time of booking. On clicking the checkout option, the fare is calculated and deducted from customer wallet. A zero balance wallet is created at the time of registration and money can be added via debit card.
