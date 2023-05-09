# StoreSystem
This is a project with this steps: 
1. CRUD for customers and products 
2. ADD and DELETE customer orders 
3. Search customers, products, and orders 
4. Search order details

IDE: intelliJ
SprinBoot: 3.0.6
Java: 17.0.2
Gradle: 7.6

The file named "StoreSystem.postman_collection.json" is attached to the project resource path, in which the urls to consume the services are versioned, this can be imported into Postman.

To take into account you must have Products registered in the MySQL database before inserting an Order.

When saving a Customer, the Address is saved.

It is not possible to save a Customer record with the same phone number or email.
