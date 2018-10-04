package com.screw.infrastructure.repository;

import com.screw.domain.customer.Customer;
import com.screw.domain.customer.CustomerRepository;
import com.screw.infrastructure.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String CUSTOMER_TABLE = "customer";
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void create(Customer customer) {
        if (customerMapper.create(customer) == 0) {
            TableException.throwTableException(CUSTOMER_TABLE, TableOperation.CREATE);
        }
    }

    @Override
    public Customer selectByKey(int id) {
        return customerMapper.selectByKey(id);
    }

    @Override
    public Customer selectOneBySpecification(Customer example) {
        return customerMapper.selectOneBySpecification(example);
    }

    @Override
    public List<Customer> selectAll() {
        return customerMapper.selectAll();
    }

    @Override
    public List<Customer> selectBySpecification(Customer example) {
        return customerMapper.selectBySpecification(example);
    }

    @Override
    public void update(Customer customer) {
        if (customerMapper.update(customer) == 0) {
            TableException.throwTableException(CUSTOMER_TABLE, TableOperation.UPDATE);
        }
    }

    @Override
    public void delete(Customer customer) {
        if (customerMapper.delete(customer) == 0) {
            TableException.throwTableException(CUSTOMER_TABLE, TableOperation.DELETE);
        }
    }
}
