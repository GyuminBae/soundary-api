package io.github.eappezo.soundary.core.persistence.domain;

public interface PersistenceOperationGateway {

    <T> T executeReadOnlyOperation(PersistenceOperation<T> operation);

    <T> T executeOperation(PersistenceOperation<T> operation);

}
