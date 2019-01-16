package com.mapscience.core.util;

import com.mapscience.core.exception.ProjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Serializable工具(JDK)
 */
public class SerializableUtil {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializableUtil.class);


    /**
     * 序列化
     *
     * @param object
     * @return byte[]
     */
    public static byte[] serializable(Object object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            LOGGER.error("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
            throw new ProjectException(500, "SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                LOGGER.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
                throw new ProjectException(500, "SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            }
        }
    }


    /**
     * 反序列化
     *
     * @param bytes
     * @return java.lang.Object
     */
    public static Object unserializable(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            LOGGER.error("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
            throw new ProjectException(500, "SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            throw new ProjectException(500, "SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                LOGGER.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
                throw new ProjectException(500, "SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            }
        }
    }
}
