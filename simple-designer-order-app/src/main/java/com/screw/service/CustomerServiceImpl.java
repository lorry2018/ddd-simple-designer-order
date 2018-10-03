package com.screw.service;

import com.screw.CustomerService;
import com.screw.domain.customer.Customer;
import com.screw.domain.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean canCreateDesignerOrder(int customerId) {
        Customer customer = customerRepository.selectByKey(customerId);
        if (customer == null) {
            return false;
        }

        return customer.canCreateDesignerOrder();
    }

    @Override
    public void enable(int customerId) {
        Customer customer = customerRepository.selectByKey(customerId);
        if (customer == null) {
            return;
        }

        customer.enable();

        customerRepository.update(customer);
    }

    @Override
    public void disable(int customerId) {
        Customer customer = customerRepository.selectByKey(customerId);
        if (customer == null) {
            return;
        }

        customer.disable();

        customerRepository.update(customer);
    }
}
