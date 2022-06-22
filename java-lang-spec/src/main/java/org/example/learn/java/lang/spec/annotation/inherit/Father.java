package org.example.learn.java.lang.spec.annotation.inherit;

import org.apache.commons.lang3.builder.ToStringBuilder;

@InheritableTrait(name = "身高")
@NonInheritableTrait(name = "低度近视")
@Scar(name = "腿伤的伤疤", cause = "踢球时留下的")
@Handsome(level = "非常帅气")
public class Father {

    private String name;

    public Father(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
