package org.example.learn.java.lang.spec.serialization;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 被序列化的类
 * <p>
 * note: 通过反序列化来实例化对象的时候,
 * 1. 对象实例不通过构造函数
 * 2. 字段赋值也不通过setter方法
 */
public class SerializationPerson2 implements Serializable {

    private static final long serialVersionUID = 3408169507716120674L;

    private int id;
    private String name;
    // 序列化时间戳
    private long serialTimeMillis = -1;

    public SerializationPerson2() {
        System.out.println(SerializationPerson2.class.getSimpleName() + " is instantiating");
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

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        System.out.println(" 调用 readObject() !!!");
        id = in.readInt();
        name = (String) in.readObject();
        serialTimeMillis = in.readLong();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println(" 调用 writeObject() !!!");
        out.writeInt(id);
        out.writeObject(name);
        out.writeLong(System.currentTimeMillis());
    }
}
