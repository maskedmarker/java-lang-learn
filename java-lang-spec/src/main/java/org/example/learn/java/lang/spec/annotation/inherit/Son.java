package org.example.learn.java.lang.spec.annotation.inherit;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Handsome(level = "还行")
public class Son extends Father {

    public Son(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
