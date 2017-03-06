# The Dream Shop 
Given an online shop which sells beverages and snacks for some virtual currency. Let's call the currency coins. So each customer of the shop has some credit which can be used to make purchases. 
Your task is to implement back-end for checkout process of the shopping card of the customer. 

## The original task

### Input 
- the list of items identified by SKU (stock keeping unit, e.g. 10001-1290-S). Item consists of name, price (in coins), the amount of items in stock (which can be zero). 
- the list of customers identified by ID (some long integer value, e.g. 100001). User consists of user-name, email and available credit (in coins, which also can be zero). 
- the list of customer's shipping addresses identified by ID (also long integer value) to which it is possible to ship purchased items after checkout. Shipping address consists of postal code, street, house and flat. 
### Output 
- the final result of checkout is user's order. The order consists of coins total and the list of items ordered. 
### Assumptions 
- Assuming that all customers are living in the same town.  
- Assuming that logic for creating deleting and updating listed entities is already present in the system. So you do not need to implement it. But you still need some data in you database. So it is required that you design the schema (as simple as possible) and fill the database with some data of your choice. 
### REST resources to implement 
- items in stock (read-only) 
- customers (read-only) 
- customer's shipping addresses (read-only) 
- orders (read/write) 
### Please Notice 
- pay attention for validity of the data submitted to the service 
- do not forget about case when item is not available in the stock 
- do not forget about case when customer has insufficient credit 
### Technical Requirements 
- the [Maria DB 10.1](https://mariadb.org) should be used as DB 
- you need to provide provision script to fill database with initial data 
- assume that DB is running on the same host as your service (localhot:3306) 
- the resource payload format should be JSON
- the source code should be easily built with Maven (just by single command `mvn install`, no additional steps) 
- the `mvn exec` should be used to run the service you wrote (no external application servers!) 
- your service should bind and listen for requests at the port 8080 
- you also need to provide request examples to query your service and perform the checkout (ready to use `curl` examples are more then welcome)

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


