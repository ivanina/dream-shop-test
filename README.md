# The Dream Shop 
Given an online shop which sells beverages and snacks for some virtual currency. Let's call the currency coins. So each customer of the shop has some credit which can be used to make purchases. 
Your task is to implement back-end for checkout process of the shopping card of the customer. 


## Configuration
Change DB access in 
```
application.properties
```
Default configuration
```
jdbc:mariadb://localhost:3306/test
username: test
password: test
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

List of Customers
```
http://localhost:8080/customer/list
```
View customer
```
http://localhost:8080/customer/view?email=smith@ohn.com
```
or
```
http://localhost:8080/customer/view?id=2
```
Set credit for customer
```
http://localhost:8080/customer/set-credit?id=1&credit=45
```
or
```
http://localhost:8080/customer/set-credit?email=smith@ohn.com&credit=45
```
Create customer (without validation by email and without email validation)
```
http://localhost:8080/customer/create?name=Test-Name&email=test-email@email.com&credit=111
```
List of products
```
http://localhost:8080/product/list
```

List of orders
```
http://localhost:8080/order/list
```
Create order for customer
```
http://localhost:8080/order/new?customer_id=2
```
List of orders by customer
```
http://localhost:8080/order/list?customer_id=2
```
Add product to order
```
http://localhost:8080/order/add/product?order_id=1&product_id=3
```
Add address to order 
```
http://localhost:8080/order/add/address?order_id=1&address_id=1
```
Checkout
```
http://localhost:8080/order/checkout?order_id=1
```


