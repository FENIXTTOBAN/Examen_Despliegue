package pe.edu.upeu.api_orders.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.upeu.api_orders.model.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSteps {

    private ResponseEntity<Order> response;
    private RestTemplate restTemplate = new RestTemplate();

    @Given("the order API is up")
    public void the_order_api_is_up() {
        // El servidor ya lo levanta CucumberSpringConfiguration
    }

    @When("I send a POST request to {string} with customer {string} and amount {double}")
    public void i_send_a_post_request(String path, String customer, Double amount) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setAmount(amount);
        
        response = restTemplate.postForEntity("http://localhost:8080" + path, order, Order.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }
}