package io.github.eappezo.soundary.core.persistence.domain;

public interface PersistenceOperationGateway {

    <T> T commitReadOnlyOperation(PersistenceOperation<T> operation);

    <T> T commitOperation(PersistenceOperation<T> operation);

}
