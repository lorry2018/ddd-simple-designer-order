package com.screw.service.impl;

import com.screw.BusinessException;
import com.screw.domain.customer.Customer;
import com.screw.domain.customer.CustomerRepository;
import com.screw.service.AppExceptionMessage;
import com.screw.service.CustomerService;
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
            BusinessException.throwException(AppExceptionMessage.CUSTOMER_NOT_EXIST_CODE, AppExceptionMessage.CUSTOMER_NOT_EXIST, customerId);
        }

        return customer.canCreateDesignerOrder();
    }

    @Override
    public void enable(int customerId) {
        Customer customer = customerRepository.selectByKey(customerId);
        if (customer == null) {
            BusinessException.throwException(AppExceptionMessage.CUSTOMER_NOT_EXIST_CODE, AppExceptionMessage.CUSTOMER_NOT_EXIST, customerId);
        }

        customer.enable();

        customerRepository.update(customer);
    }

    @Override
    public void disable(int customerId) {
        Customer customer = customerRepository.selectByKey(customerId);
        if (customer == null) {
            BusinessException.throwException(AppExceptionMessage.CUSTOMER_NOT_EXIST_CODE, AppExceptionMessage.CUSTOMER_NOT_EXIST, customerId);
        }

        customer.disable();

        customerRepository.update(customer);
    }
}
