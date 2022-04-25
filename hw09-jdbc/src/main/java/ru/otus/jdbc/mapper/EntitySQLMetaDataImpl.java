package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{

    private final EntityClassMetaData<T> metaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> metaData) {
        this.metaData = metaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("SELECT %s FROM %s", getFieldNames(), getTableName());
    }

    private String getTableName() {
        return metaData.getName();
    }

    private String getFieldNames() {
        return metaData.getAllFields().stream().map(Field::getName).collect(Collectors.joining(","));
    }

    private String getFieldNamesWithoutId() {
        return metaData.getFieldsWithoutId().stream().map(Field::getName).collect(Collectors.joining(","));
    }
    @Override
    public String getSelectByIdSql() {
        return String.format("SELECT %s FROM %s WHERE %s = ?", getFieldNames(), getTableName(), getIdName());
    }

    private Object getIdName() {
        return metaData.getIdField().getName();
    }

    @Override
    public String getInsertSql() {
        return String.format("INSERT INTO %s(%s) VALUES(%s)", getTableName(), getFieldNamesWithoutId(), getValuesStringWithoutId());
    }

    private Object getValuesStringWithoutId() {
        return Stream.generate(() ->"?").limit(metaData.getFieldsWithoutId().size()).collect(Collectors.joining(","));
    }

    @Override
    public String getUpdateSql() {
        return null;
    }
}
