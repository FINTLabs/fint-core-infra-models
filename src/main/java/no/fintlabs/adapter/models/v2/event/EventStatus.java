package no.fintlabs.adapter.models.v2.event;

/**
 * How the adapter handled the event.
 */
public enum EventStatus {
    /**
     * The operation was performed.
     */
    SUCCEEDED,
    /**
     * The source system refused the request, for example because of invalid data
     * or because a read found more resources than {@link EventRequest#getMaxResults()} allows.
     * The client gets a 400.
     */
    REJECTED,
    /**
     * The request conflicts with the data in the source system.
     * The response carries the resource as it is in the source system. The client gets a 409.
     */
    CONFLICT,
    /**
     * Something went wrong while handling the event. The client gets a 500.
     */
    ERROR
}
