package com.apisaes.task.validator;

import hr.aaa.test.v0.datadetails.DataDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

@Slf4j
public class SchemaValidator {

    private SchemaValidator() {
    }

    public static void validateXmlSchema(DataDetails dataDetails) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DataDetails.class);
            JAXBSource jaxbSource = new JAXBSource(jaxbContext, dataDetails);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new ClassPathResource("schema/dataDetails.xsd").getFile());
            schemaFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            Validator validator = schema.newValidator();
            validator.setErrorHandler(new SchemaErrorHandler());
            validator.validate(jaxbSource);
        } catch (SAXException | IOException | JAXBException e) {
            log.error(e.getMessage());
        }

    }

}
