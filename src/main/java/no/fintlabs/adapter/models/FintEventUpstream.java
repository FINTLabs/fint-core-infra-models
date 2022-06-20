package no.fintlabs.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FintEventUpstream {
    /**
     * GUID for correlation ID. The same ID should follow the request both upstream and downstream.
     */
    String corrId;

    /**
     * OrgId for the current customer.
     */
    private String orgId;

    /**
     * See {@link AdapterContract#getAdapterId()}
     */
    private String adapterId;

    /**
     * When the event was received.
     */
    private long acceptedAt;

    /**
     * When the event was handled.
     */
    private long handledAt;

    /**
     * If the event has failed.
     */
    private boolean failed;

    /**
     * A sensible explanation that says something about why the incident has failed.
     */
    private boolean errorMessage;
}
