package com.screw.infrastructure.repository;

import com.screw.domain.customer.Customer;
import com.screw.domain.customer.CustomerRepository;
import com.screw.infrastructure.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void create(Customer customer) {

    }

    @Override
    public Customer selectByKey(int id) {
        return null;
    }

    @Override
    public Customer selectOne(Customer example) {
        return null;
    }

    @Override
    public List<Customer> selectAll() {
        return null;
    }

    @Override
    public List<Customer> selectBySpecification(Customer example) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
