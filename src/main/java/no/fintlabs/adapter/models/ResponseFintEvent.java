package no.fintlabs.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseFintEvent<T> {
    /**
     * GUID for correlation ID. The same ID should follow the request both upstream and downstream.
     */
    private String corrId;

    /**
     * OrgId for the current customer.
     */
    private String orgId;

    /**
     * See {@link AdapterContract#getAdapterId()}
     */
    private String adapterId;

    /**
     * When the event was handled.
     */
    private long handledAt;

    /**
     * The SyncPageEntry of the object that the event produced. The object should be a FINT resource.
     */
    private SyncPageEntry<T> value;

    /**
     * If the event has failed.
     */
    private boolean failed;

    /**
     * A sensible explanation that says something about why the incident has failed.
     */
    private String errorMessage;
}
