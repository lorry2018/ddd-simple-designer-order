package com.screw.domain;

import java.io.Serializable;

public interface Entity<T> extends Serializable {
    boolean sameIdentityAs(T other);
}