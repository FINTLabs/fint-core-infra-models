package no.fintlabs.adapter.operation;

/**
 * The type of operation to be performed by the adapter
 */
public enum OperationType {
    CREATE,
    UPDATE,
    VALIDATE,
    DELETE,
    /**
     * Read resources straight from the source system. An adapter only gets these events for the resources
     * it lists with READ in {@link no.fintlabs.adapter.models.AdapterContract#getEventCapabilities()}.
     */
    READ
}
