/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HibernateSequence implements Serializable {

    private static final long serialVersionUID = 875365682;

    private Long nextVal;

    public HibernateSequence() {}

    public HibernateSequence(HibernateSequence value) {
        this.nextVal = value.nextVal;
    }

    public HibernateSequence(
        Long nextVal
    ) {
        this.nextVal = nextVal;
    }

    public Long getNextVal() {
        return this.nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HibernateSequence (");

        sb.append(nextVal);

        sb.append(")");
        return sb.toString();
    }
}
