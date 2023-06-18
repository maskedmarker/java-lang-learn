package org.example.learn.java.lang.spec.serialization;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.*;

/**
 * 被序列化的类
 * <p>
 * note: 通过反序列化来实例化对象的时候,
 * 1. 对象实例不通过构造函数
 * 2. 字段赋值也不通过setter方法
 */
public class SerializationPerson3 implements Externalizable {

    private static final long serialVersionUID = 3408169507716120674L;

    private int id;
    private String name;
    // 序列化时间戳
    private long serialTimeMillis = -1;

    public SerializationPerson3() {
        System.out.println(SerializationPerson3.class.getSimpleName() + " is instantiating");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                .append("id", id)
                .append("name", name)
                .append("serialTimeMillis", serialTimeMillis)
                .toString();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println(" 调用 readExternal() !!!");
        id = in.readInt();
        name = (String) in.readObject();
        serialTimeMillis = in.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println(" 调用 writeExternal() !!!");
        out.writeInt(id);
        out.writeObject(name);
        out.writeLong(System.currentTimeMillis());
    }
}
