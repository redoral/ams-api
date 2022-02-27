# AMS API (Spring Boot)

## Project Description

This project is an Account Management System RESTful API that lets users get, create, delete, and update users, accounts, transactions, and more. 
***This README is a work-in-progress and is not complete documentation of the API at it's current state.***

## Technologies Used

- Java 8
- Spring Boot
- PostgreSQL
- Maven

## Features

- Can create, read, update, and delete single and all and Users from DB.
- Can create, read, update, and delete single all Accounts from DB.
- Can create, read, update, and delete single all Customers from DB.
- Can create, read, update, and delete single all Roles from DB.
- Can create, read, update, and delete single all Transactions from DB.
- Can get Transactions by Account Number.
- Can get 5 most recent transactions by Account Number.
- Can get Transactions by specified date range.
- Can get Accounts by Customer.
- Custom exceptions.

To-do list:

- Add unit testing.

## Usage

The following API calls can be made.
A `:` in front of a portion of the path means to replace with desired value.
All request/response bodies are in JSON.

#### Base endpoint
The base endpoint for all entities is: `/api/v1`<br>
So, for example, you want to get roles, the complete endpoint would be: `/api/v1/roles`

#### Roles

- `GET` to `/roles` will return all roles.
- `GET` to `/roles/{role_id}` will return a role by it's ID.
- `POST` to `/roles` will create a new role and return that new role.
  ```JSON
  {
     "name": string
  }
  ```
- `DELETE` to `/roles/{role_id}` will delete a role.


#### Users

- `GET` to `/users` will return all roles.
- `GET` to `/users/{user_id}` will return a user by it's ID.
- `POST` to `/users` will create a new user and customer and return that new user with the customer and role it's related to.
  ```JSON
  {
    "username": string,
    "password": string,
    "customer": {
        "pan": integer,
        "citizen_uid": integer,
        "name": string,
        "address": string,
        "email": string,
        "dob": string
    },
    "role": {
        "role_id": integer
    }
  }
  ```
- `DELETE` to `/users/{user_id}` will delete a user by it's ID and the customer it's related to.

#### Account

- `GET` to `/accounts` will return all accounts.
- `GET` to `/accounts/{account_number}` will return an account by it's account number.
- `GET` to `/accounts/customer/{customer_id}` will return accounts for a specific customer by their customer ID.
- `POST` to `/accounts` will create a new account and return that new account.
  ```JSON
  {
    "current_balance": double,
    "account_type": string,
    "customer": {
        "customer_id": integer
    }
  }
  ```
  Note that `customer_id` is REQUIRED.
- `DELETE` to `/accounts/{account_id}` will delete an account by it's ID.

#### Transactions

- `GET` to `/transactions` will return all transactions
- `GET` to `/transactions/{transaction_id}` will return a transaction by it's ID.
- `GET` to `/transactions/account/{account_number}` will return transactions from an account by it's account number.
- `GET` to `/transactions/account/{account_number}/5` will return the 5 most recent transactions from an account by it's account number.
- `GET` to `/transactions/account/{account_number}/{timestamp1}/{timestamp2}` will return transactions from an account by it's account number and given date range. Timestamps have to be in `ISO 8601` format.
- `POST` to `/transactions` will create a new transaction and return that new transaction.
  ```JSON
  {
    "transaction_ref_num": int,
    "transaction_date": string,
    "transaction_type": string,
    "transaction_sub_type": string,
    "current_balance": double,
    "account": {
        "account_number": long
    }
  }
  ```
- `DELETE` to `/transactions/{transaction_id}` will delete a transaction by it's ID.


## License

[![MIT](https://img.shields.io/github/license/RevatureRobert/2106Jun07RNCN-2-p2-be?style=for-the-badge)](https://github.com/RevatureRobert/2106Jun07RNCN-2-p2-be/blob/417cce5cafa0f36f638b138d9709e1a17a31215a/LICENSE)

![Built with Love](https://forthebadge.com/images/badges/built-with-love.svg)
