package com.pepit.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericsConverter<Entity, Dto> {
    public abstract Dto entityToDto(Entity entity);
    public abstract Entity dtoToEntity(Dto dto);

    public List<Dto> entityListToDtoList(List<Entity> entityList){
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Entity> dtoListToEntityList(List<Dto> dtoList) {
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
