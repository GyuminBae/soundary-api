package io.github.eappezo.soundary.core.persistence.domain;

@FunctionalInterface
public interface PersistenceOperation<T> {

    T execute();

}
