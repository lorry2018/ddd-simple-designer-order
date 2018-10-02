package com.screw.infrastructure.mapper;

import com.screw.domain.customer.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
