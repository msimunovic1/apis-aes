package com.apisaes.task.mvc.converters;

public interface Converter<DomainType, CommandType>{
    DomainType convertToDomain(CommandType command);
}
