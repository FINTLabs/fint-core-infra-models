package no.fintlabs.adapter.models.event;

/**
 * The response status indicates the result of the event from the adapter.
 * The statuses can be:
 * <ul>
 * <li>{@link #ACCEPTED}</li>
 * <li>{@link #REJECTED}</li>
 * <li>{@link #ERROR}</li>
 * <li>{@link #CONFLICT}</li>
 * </ul>
 */
public enum ResponseStatus {

    /**
     * The event request was accepted. For example the update of the resource was successful.
     */
    ACCEPTED,
    /**
     * The event request was rejected. For example that the adapter does not allow the update of a resource.
     */
    REJECTED,
    /**
     * An error happened during processing of the event, for example that the db that the adapter connects to is down.
     */
    ERROR,
    /**
     * The requested action results in a conflict, for example that an event tries to update a value that another event has already updated.
     */
    CONFLICT;

    public static ResponseStatus byResponseEvent(ResponseFintEvent responseEvent) {
        if (responseEvent.isFailed()) {
            return ResponseStatus.ERROR;
        } else if (responseEvent.isRejected()) {
            return ResponseStatus.REJECTED;
        } else if (responseEvent.isConflicted()) {
            return ResponseStatus.CONFLICT;
        }

        return ResponseStatus.ACCEPTED;
    }

}
