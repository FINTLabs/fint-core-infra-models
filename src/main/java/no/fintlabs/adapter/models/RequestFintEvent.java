package no.fintlabs.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestFintEvent {
    /**
     * GUID for correlation ID. The same ID should follow the request both upstream and downstream.
     */
    String corrId;

    /**
     * OrgId for the current customer.
     */
    private String orgId;

    /**
     * Name of the FINT domain. E.g. utdanning.
     */
    private String domainName;

    /**
     * Name of the FINT package. E.g. vurdering.
     */
    private String packageName;

    /**
     * Name of the FINT class/entity. E.g. fravar
     */
    private String resourceName;

    /**
     * The current action, CREATE or UPDATE
     */
    private String action;

    /**
     * When the event was created.
     */
    private long created;

    /**
     * When the event should be considered expired.
     */
    private long deadline;

    /**
     * When the event was solved by the adapter
     */
    private long solvedAt;

    /**
     * The object to which the event applies
     */
    private Serializable value;
}
