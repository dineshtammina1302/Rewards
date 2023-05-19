# Rewards
RewardsDemo

Components:

TransactionEntity - bean to represent transaction consisting customerId, amount, transaction date and rewards.

TransactionReposituy: JPA Class to represent Transaction Entity for database  operations

Transactions Service: Service class to perform business logic (middle ware)

Transaction Controller : Represents End points of the each operations


Created Endpoints: 

1 ) /getRewards
localhost:8080/getRewards?startDate=2022-08-01&endDate=2023-03-29

2)/getRewards/{customerId}
localhost:8080/getRewards/1056?startDate=2022-08-01&endDate=2023-03-29

3)/transactions
localhost:8080/transactions/

4)/transactions/{customerId}
localhost:8080/transactions/1043

Database schema : h2 database
