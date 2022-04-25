package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {
    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private Class<T> tCLass;
    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, Class<T> tClass) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.tCLass = tClass;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), getRs);
    }

    @Override
    public List<T> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long insert(Connection connection, T client) {
        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), insertParams(client));
    }

    private List<Object> insertParams(T client) {
        return Arrays.stream(client.getClass().getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class)).map(field -> {
            try {
                if (!field.canAccess(client)) {
                    field.setAccessible(true);
                } else {
                    return field.get(client);
                }
                var val = field.get(client);
                field.setAccessible(false);
                return val;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public void update(Connection connection, T client) {
        throw new UnsupportedOperationException();
    }

    private final Function<ResultSet, T> getRs =   rs -> {
            try {
                if (rs.next()) {
                    var tObj = tCLass.newInstance();
                    Arrays.stream(tObj.getClass().getDeclaredFields()).forEach( field -> {
                        try {
                            field.setAccessible(true);
                            field.set(tObj, getValue(rs, field));
                        } catch (IllegalAccessException | SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return tObj;
                }
                return null;
            } catch (Exception e) {
                throw new DataTemplateException(e);
            }
        };

    private Object getValue(ResultSet rs, Field field) throws SQLException {
        if(field.getType() == Long.class) {
            return rs.getLong(field.getName());
        }
        if(field.getType() == String.class) {
            return rs.getString(field.getName());
        }
        return null;
    }


}
