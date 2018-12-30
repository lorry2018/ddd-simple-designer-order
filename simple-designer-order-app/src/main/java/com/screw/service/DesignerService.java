package com.screw.service;

public interface DesignerService {
    void enable(int designerId);
    void disable(int designerId);
    void changePrice(int designerId, float priceByDay);
}
