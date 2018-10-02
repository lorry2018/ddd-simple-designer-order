package com.screw.domain.designer;

import com.screw.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @Override
    public boolean sameIdentityAs(Designer other) {
        return this.equals(other);
    }
}
