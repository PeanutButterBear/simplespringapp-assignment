package edu.cscc.controller;
//Calvin Gates, 11/7/2022, using spring boot to create API to (fake) database through Java
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cscc.model.CreateOrderRequest;
import edu.cscc.model.Order;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
	
	@SuppressWarnings("serial")
	Map<Integer, Order> orders = new HashMap<Integer, Order>() {{
	     put(1, new Order(1,"Bob", "Smith", 5, 30.49));
	     put(2, new Order(2,"Sally", "Roberts", 10, 120.55));
	     put(3, new Order(3,"Oscar", "Rodriguez", 1, 11.20));
	    }};
	 
	int nextId = orders.size() + 1;

	@GetMapping()
	public ResponseEntity<Collection<Order>> getOrders() {
		return new ResponseEntity<Collection<Order>>(orders.values(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable int id) {
		if(orders.containsKey(id)) {
			return new ResponseEntity<Order>(orders.get(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<Order> postOrder(@RequestBody CreateOrderRequest order) {
		// Next line is same as: int id = nextId; nextId++;
		int id = nextId++;
		orders.put(id, new Order(id,order.firstName, order.lastName, order.quantity, order.price));
		return new ResponseEntity<Order>(orders.get(id), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> putOrder(@PathVariable int id, @RequestBody CreateOrderRequest order) {
		if(orders.containsKey(id)) {
			Order updatedOrder = new Order(id,order.firstName, order.lastName, order.quantity, order.price);
			orders.put(id, updatedOrder);
			return new ResponseEntity<Order>(orders.get(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Order> deleteItem(@PathVariable int id) {
		if(orders.containsKey(id)) {
			orders.remove(id);
			return new ResponseEntity<Order>(orders.get(id), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
