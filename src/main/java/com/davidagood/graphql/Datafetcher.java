package com.davidagood.graphql;

import com.davidagood.graphql.types.Customer;
import com.davidagood.graphql.types.Order;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@DgsComponent
public class Datafetcher {

    static final Logger log = LoggerFactory.getLogger(Datafetcher.class);

    @DgsData(parentType = "Query", field = "customer")
    public Customer customer(String id) {
        log.info("resolving customer");
        return Customer.newBuilder()
            .id(id)
            .firstName("Sam")
            .lastName("Junior")
            .build();
    }

    @DgsData(parentType = "Customer", field = "orders")
    public List<Order> orders(DataFetchingEnvironment dataFetchingEnvironment) {
        log.info("resolving customer orders");
        Customer customer = dataFetchingEnvironment.getSource();
        String customerId = customer.getId();
        return List.of(Order.newBuilder().id("999").total(100d).customerId(customerId).build());
    }

}
