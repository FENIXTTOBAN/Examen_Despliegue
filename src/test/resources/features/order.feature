Feature: Order API
    Scenario: Create a new order via REST
        Given the order API is up
        When I send a POST request to "/api/orders" with customer "Jhan Arly" and amount 1200.0
        Then the response status should be 201