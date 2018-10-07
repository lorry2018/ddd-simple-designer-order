package com.screw.infrastructure.mapper;

import com.screw.DomainConfiguration;
import com.screw.domain.customer.Address;
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
@EnableAutoConfiguration
public class CustomerMapperTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void crud() {
        Customer customer = newCustomer();
        int affectRows = customerMapper.create(customer);
        Assert.assertEquals(1, affectRows);

        List<Customer> customers = customerMapper.selectAll();
        Assert.assertEquals(2, customers.size());
        Assert.assertEquals(1, customers.get(0).getDesignerOrders().size());

        customer = customerMapper.selectByKey(1);
        Assert.assertTrue(customer != null);

        customer = customerMapper.selectOneBySpecification(newCustomer());
        Assert.assertTrue(customer != null);

        customers = customerMapper.selectBySpecification(newCustomer());
        Assert.assertEquals(1, customers.size());

        customer = customers.get(0);
        customer.setName("lorry3");
        affectRows = customerMapper.update(customer);
        Assert.assertEquals(1, affectRows);

        affectRows = customerMapper.delete(customer);
        Assert.assertEquals(1, affectRows);
    }

    private Customer newCustomer() {
        Customer customer = new Customer();
        customer.setName("lorry2");
        customer.setPhone("15809233559");
        customer.setEnabled(true);

        Address address = new Address();
        address.setProvince("shaanxi");
        address.setCity("xian");
        address.setZone("gaoxin");
        address.setPostCode("710075");
        address.setAddress("address");

        customer.setAddress(address);
        return customer;
    }
}
