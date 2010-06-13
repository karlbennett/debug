package org.youthnet.debug.domain.core;

import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * User: Olivier Van Acker (olivier.van.acker@youthnet.org)
 * Date: 11-Jan-2010
 */
@Entity
@Table(name = "MetaData")
public class MetaData extends GenericDTO {

    @Transient
    private Logger log = Logger.getLogger(this.getClass().getName());

    @Column(length = 86)
    private String type;

    @Column(columnDefinition = "blob")
    private byte[] serializedObject;

    @Column(columnDefinition = "blob")
    private byte[] digest;

    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    private UuidType dtoId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getSerializedObject() {
        return serializedObject;
    }

    public void setSerializedObject(byte[] serializedObject) {
        MessageDigest md5;
        byte[] digest;
        try {
            md5 = MessageDigest.getInstance("MD5");
            this.digest = md5.digest(serializedObject);
        } catch (NoSuchAlgorithmException e) {
            log.severe(e.toString());
        }
        this.serializedObject = serializedObject;
    }

    public byte[] getDigest() {
        return digest;
    }

    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    public UuidType getDtoId() {
        return dtoId;
    }

    public void setDtoId(UuidType dtoId) {
        this.dtoId = dtoId;
    }
}
