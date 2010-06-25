package org.youthnet.debug.domain.common.impl;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.util.conversion.UuidConverter;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

/**
 * User: karl
 * Date: 18-May-2010
 */
public class UuidTypeImpl implements UuidType {

    private UUID uuid;

    public UuidTypeImpl() {
        super();
    }

    public UuidTypeImpl(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    private static final String CAST_EXCEPTION_TEXT = " cannot be cast to a org.youthnet.vbase.dao.custom.UuidUserType.";

    @Override
    public int[] sqlTypes() {
        return new int[]{Hibernate.BINARY.sqlType()};
    }

    @Override
    public Class returnedClass() {
        return UuidTypeImpl.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null && y == null) {
            return true;
        } else if (x == null || y == null) {
            return false;
        }
        if (!UuidTypeImpl.class.isAssignableFrom(x.getClass())) {
            throw new HibernateException(x.getClass().toString() + CAST_EXCEPTION_TEXT);
        } else if (!UuidTypeImpl.class.isAssignableFrom(y.getClass())) {
            throw new HibernateException(y.getClass().toString() + CAST_EXCEPTION_TEXT);
        }

        UUID a = ((UuidTypeImpl) x).getUuid();
        UUID b = ((UuidTypeImpl) y).getUuid();

        return a.equals(b);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (!UuidTypeImpl.class.isAssignableFrom(x.getClass())) {
            throw new HibernateException(x.getClass().toString() + CAST_EXCEPTION_TEXT);
        }
        UUID uuid = ((UuidTypeImpl) x).getUuid();
        return uuid.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
        Object value = resultSet.getObject(names[0]);
        if (value == null) {
            return null;
        } else {
            return new UuidTypeImpl(UuidConverter.convertByteArrayToUuid((byte[])value));
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.NULL);
            return;
        }

        if (!UuidTypeImpl.class.isAssignableFrom(value.getClass())) {
            throw new HibernateException(value.getClass().toString() + " with value " + value.toString() + CAST_EXCEPTION_TEXT);
        }

        preparedStatement.setObject(index, UuidConverter.convertUuidArrayToByteArray(((UuidTypeImpl) value).getUuid()));

    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return (UuidTypeImpl) value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof UuidTypeImpl) {
            UuidTypeImpl uuidCompare = (UuidTypeImpl) object;
            if(this.uuid.equals(uuidCompare.getUuid())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    public static UuidTypeImpl fromString(String uuidString) {
        return new UuidTypeImpl(UUID.fromString(uuidString));
    }

}
