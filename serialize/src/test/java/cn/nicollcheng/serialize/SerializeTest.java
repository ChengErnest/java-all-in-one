package cn.nicollcheng.serialize;

import cn.nicollcheng.serialize.avro.PersonAvro;
import cn.nicollcheng.serialize.domain.Person;
import cn.nicollcheng.serialize.protobuf.PersonProto;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/9/14 10:41.
 */
public class SerializeTest {
    @Test
    public void testJdk() throws IOException, ClassNotFoundException {
        String filename = "person";
        Person person = new Person().setName("Nicoll")
                .setId(1).setEmail("nicollcheng@qq.com");
        // 序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(person);
        }
        // 反序列化
        File file = new File(filename);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object object = ois.readObject();
            System.out.println(Arrays.toString(Files.readAllBytes(Paths.get(file.getPath()))) + "\n" + "length: " + file.length() + "\n" + object);
        }
    }

    @Test
    public void testXml() {
        Person person = new Person().setName("Nicoll")
                .setId(1).setEmail("nicollcheng@qq.com");
        // 序列化
        String xml = new XStream(new DomDriver()).toXML(person);
        // 反序列化
        Object unSerialize = new XStream(new DomDriver()).fromXML(xml);
        System.out.println("" + xml + "\nlength: " + xml.length() + "\n" + unSerialize);
    }

    @Test
    public void testJson() {
        Person person = new Person().setName("Nicoll")
                .setId(1).setEmail("nicollcheng@qq.com");
        // 序列化
        String serialize = JSONObject.toJSONString(person);
        // 反序列化
        Person unSerialize = JSONObject.parseObject(serialize, Person.class);
        System.out.println("" + serialize + "\nlength: " + serialize.length() + "\n" + unSerialize);
    }

    @Test
    public void testProtoBuf() throws InvalidProtocolBufferException {
        // 序列化
        PersonProto.Person person = PersonProto.Person.newBuilder().setName("Nicoll")
                .setId(1).setEmail("nicollcheng@qq.com").build();
        byte[] serializeBytes = person.toByteArray();
        // 反序列化
        PersonProto.Person unSerialize = PersonProto.Person.parseFrom(serializeBytes);
        System.out.println(Arrays.toString(serializeBytes) + "\nlength: " + serializeBytes.length + "\n" + unSerialize);
    }

    @Test
    public void testAvro() throws IOException {
        // 序列化
        PersonAvro person = PersonAvro.newBuilder().setName("Nicoll")
                .setId(1)
                .setEmail("nicollcheng@qq.com")
                .build();
        byte[] serializeBytes = person.toByteBuffer().array();
        // 反序列化
        PersonAvro decode = PersonAvro.getDecoder().decode(serializeBytes);
        System.out.println(Arrays.toString(serializeBytes) + "\nlength: " + serializeBytes.length + "\n" + decode);
    }
}
