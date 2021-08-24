package com.apisaes.task.validator;

import com.apisaes.task.exception.MethodNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

@Slf4j
public class SchemaErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) {
        log.error("WARNING : {}", exception.getMessage());
        throw new MethodNotAllowedException(exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        log.error("ERROR : {}", exception.getMessage());
        throw new MethodNotAllowedException(exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) {
        log.error("FATAL ERROR : {}", exception.getMessage());
        throw new MethodNotAllowedException(exception.getMessage());
    }
}
