package com.apisaes.task.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XmlUtil {

    public static <T> String toString(Object object, Class<T> clazz) throws JAXBException {
        Marshaller marshaller = createMarshaller(clazz);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);

        return stringWriter.toString();
    }

    private static <T> Marshaller createMarshaller(Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return jaxbContext.createMarshaller();
    }
}
