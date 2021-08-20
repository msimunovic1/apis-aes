package com.apisaes.task.commands;

public interface Converter<DomainType, CommandType>{
    DomainType convertToDomain(CommandType command);
}
