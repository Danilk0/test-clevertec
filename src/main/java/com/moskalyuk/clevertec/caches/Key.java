package com.moskalyuk.clevertec.caches;

import com.moskalyuk.clevertec.database.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Key<V extends BaseEntity> {
    private Integer id;
    private Class<V> clazz;


    public Key(Integer id, Class<V> clazz) {
        this.id = id;
        this.clazz = clazz;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(clazz).toHashCode();
    }
}
