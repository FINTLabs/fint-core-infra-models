package no.fintlabs.adapter.models.v2.event;

/**
 * The operation the adapter is asked to perform.
 */
public enum EventOperation {
    /**
     * Create a new resource in the source system.
     */
    CREATE,
    /**
     * Update an existing resource in the source system.
     */
    UPDATE,
    /**
     * Check a resource against the source system without saving it.
     */
    VALIDATE,
    /**
     * Delete a resource in the source system.
     */
    DELETE,
    /**
     * Read resources straight from the source system, either by a filter or by one id.
     * Read results are only returned on the event, they are never written to the FINT cache.
     * The adapter only gets read events for resources where
     * {@link no.fintlabs.adapter.models.EventCapability#getOperations()} contains READ.
     */
    READ
}
