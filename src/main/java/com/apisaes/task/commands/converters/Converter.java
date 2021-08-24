package com.apisaes.task.commands.converters;

public interface Converter<DomainType, CommandType>{
    DomainType convertToDomain(CommandType command);
}
