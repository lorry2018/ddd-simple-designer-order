package com.screw.domain.order;

import com.screw.DomainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DomainConfiguration.class})
@ActiveProfiles("dev")
@EnableAutoConfiguration
public class DesignerOrderTest {

    @Test
    public void measure() {
        DesignerOrder order = DesignerOrderFactory.createOrder(1, 1);
        assertEquals(DesignerOrderState.NEW, order.getState());

        order.measure(100);
        assertEquals(DesignerOrderState.MEASURED, order.getState());
        assertEquals(100, order.getArea(), 0.01);
    }

    @Test
    public void quote() {
        DesignerOrder order = DesignerOrderFactory.createOrder(1, 1);
        order.measure(100);
        order.quote(2000, new int[] {1, 4, 4, 1});

        assertEquals(DesignerOrderState.QUOTED, order.getState());
        assertEquals(2000, order.getExpectedAmount(), 0.01);
        assertEquals(10, order.getEstimatedDays());
    }

    @Test
    public void acceptPrice() {
    }

    @Test
    public void rejectPrice() {
    }

    @Test
    public void pay() {
    }

    @Test
    public void abort() {
    }

    @Test
    public void requestCompletion() {
    }

    @Test
    public void confirmCompletion() {
    }

    @Test
    public void feedback() {
    }
}