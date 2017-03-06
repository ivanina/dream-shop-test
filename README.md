# The Dream Shop 
Given an online shop which sells beverages and snacks for some virtual currency. Let's call the currency coins. So each customer of the shop has some credit which can be used to make purchases. 
Your task is to implement back-end for checkout process of the shopping card of the customer. 


## Configuration
Change DB access in 
```
application.properties
```
## Installation
```
mvn install
```
## Run
```
java -jar ./target/DreamShopTest-1.0-SNAPSHOT.jar
```
## Test on browser or CURL

### Methods 
Prepare test data
```
http://localhost:8080/prepare-date
```
Customers
```
http://localhost:8080/customer/list
```
View customer
```
http://localhost:8080/customer/view?email=email@email.com
```
or
```
http://localhost:8080/customer/view?id=1
```
Set credit for customer
```
http://localhost:8080/customer/set-credit?id=1&credit=111
```
or
```
http://localhost:8080/customer/set-credit?email=email@email.com&credit=111
```
Create customer (without validation by email and without email validation)
```
http://localhost:8080/customer/create?name=name&email=email@email.com&credit=111
```

List of orders
```
http://localhost:8080/order/list
```
List of orders by customer (customer_id)
```
http://localhost:8080/order/list?customer_id=1
```
Add product to exists or new order
```
http://localhost:8080/order/add/product?customer_id=1&product_id=1
```
Add address to actualy order for customer
```
http://localhost:8080/order/add/address?customer_id=1&address_id=1
```
Checkout
```
http://localhost:8080/order/checkout
```


