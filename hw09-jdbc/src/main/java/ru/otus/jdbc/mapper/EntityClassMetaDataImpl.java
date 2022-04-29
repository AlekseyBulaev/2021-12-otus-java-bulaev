package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplateException;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<?> cls;

    public EntityClassMetaDataImpl(Class<?> cls) {
        this.cls = cls;
    }

    @Override
    public String getName() {
        return cls.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return (Constructor<T>) cls.getConstructor();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get constructor", e);
        }
    }

    @Override
    public Field getIdField() {
        var result = Arrays.stream(cls.getDeclaredFields()).filter(field ->
                field.isAnnotationPresent(Id.class)
        ).toList();
        if (result.size() != 1) {
            throw new RuntimeException("Id annotations must be one");
        }
        return result.get(0);
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.stream(cls.getDeclaredFields()).toList();
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Arrays.stream(cls.getDeclaredFields()).filter(field -> !field.isAnnotationPresent(Id.class)).toList();
    }
}
