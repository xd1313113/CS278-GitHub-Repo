#Description
My client and server is order process application. The client side would generate different types  of order on different items and send them to the server through socket While the server side has two thread: one is for collecting orders from client and insert into order queue and the other is to process orders according to order type. The whole project is an command pattern application, but also consider scalability. 

#How to run
The project includes unit test in the source code for BuyItemOrder class and Agent class. And the program will generate orders in the client side to test the whole project. The IP address is written in Contants class file. Change if needed. 

You just need run ./setup.sh to build, deloy and start the server and client.
