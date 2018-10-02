package com.screw.infrastructure.mapper;

import com.screw.DomainConfiguration;
import com.screw.domain.customer.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DomainConfiguration.class})
@ActiveProfiles("dev")
@EnableAutoConfiguration
public class CustomerMapperTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void create() {
        List<Customer> customers = customerMapper.selectAll();

        Assert.assertEquals(1, customers.size());
    }
}
