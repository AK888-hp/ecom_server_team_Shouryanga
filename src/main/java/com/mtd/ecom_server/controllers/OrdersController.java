package com.mtd.ecom_server.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.ecom_server.models.Orders;
import com.mtd.ecom_server.models.Product;
import com.mtd.ecom_server.repos.OrderRepo;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private static final Logger Log=LoggerFactory.getLogger(OrdersController.class);
    @Autowired OrderRepo orderRepo;
    @Tag(name="Get all orders")
    @GetMapping("/all")
	public List<Orders> getAllOrders(){
		Log.info("Fetching Orders");
		return orderRepo.findAll();
	}

    @GetMapping("/all/{id}")
	public Orders getOrderbyid(@PathVariable String id){
		Log.info("Fetching Order by id , id :"+id);
		return orderRepo.findById(id).orElse(null);
	}

    @GetMapping("/all/{userid}")
	public Orders getOrderbyuserid(@PathVariable String userid){
		Log.info("Fetching Order by userid , id :"+userid);
		return orderRepo.findById(userid).orElse(null);
	}

}
