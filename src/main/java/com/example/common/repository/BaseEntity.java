package com.example.common.repository;

import org.hibernate.proxy.HibernateProxy;

public abstract class BaseEntity<T extends BaseEntity<T>> {

    protected abstract boolean equalsId(T other);

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy
            ? ((HibernateProxy) this)
            .getHibernateLazyInitializer()
            .getPersistentClass()
            .hashCode()
            : getClass().hashCode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        return this.equalsId((T) o);
    }
}
