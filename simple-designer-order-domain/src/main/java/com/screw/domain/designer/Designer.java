package com.screw.domain.designer;

import com.screw.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

@Data
@EqualsAndHashCode(of = {"id"})
public class Designer implements Entity<Designer> {
    private int id;
    private String name;
    private String phone;
    private int age;
    private DesignerLevel level;
    private float priceByDay;
    private boolean enabled;

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void changePrice(float priceByDay) {
        Assert.isTrue(priceByDay > 0, "The price by day of designer must be bigger than 0.");

        this.priceByDay = priceByDay;
    }

    @Override
    public boolean sameIdentityAs(Designer other) {
        return this.equals(other);
    }
}
