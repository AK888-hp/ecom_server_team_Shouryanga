package com.mtd.ecom_server.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mtd.ecom_server.models.Orders;

public interface OrderRepo extends MongoRepository<Orders,String>{

}
