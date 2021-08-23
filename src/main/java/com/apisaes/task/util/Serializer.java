package com.apisaes.task.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Serializer {

    private Serializer() {
    }

    public static byte[] serialize(Object object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;

        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            log.error("Error occurred while serializing object to byte array : {}", e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                bos.close();
            } catch (IOException e) {
                log.error("Error occurred while serializing object to byte array : {}", e.getMessage());
            }
        }

        return bytes;
    }

    public static Object deserialize(byte[] byteArray) {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = null;
        Object object = null;

        try {
            in = new ObjectInputStream(bis);
            object = in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            log.error("Error occurred while deserializing object from byte array : {}", e.getMessage());
        } finally {
            try {
                bis.close();
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("Error occurred while deserializing object from byte array : {}", e.getMessage());
            }
        }

        return object;
    }
}
