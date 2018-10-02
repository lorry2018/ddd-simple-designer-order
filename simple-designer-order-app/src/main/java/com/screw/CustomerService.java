package com.screw;

public interface CustomerService {
    boolean canCreateDesignerOrder(int customerId);
    void enable(int customerId);
    void disable(int customerId);
}
