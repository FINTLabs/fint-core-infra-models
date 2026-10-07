package no.fintlabs.adapter.models.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fintlabs.adapter.operation.OperationType;

/**
 * Represents a request to the adapter
 * <p>
 * A {@link OperationType#READ} request asks the adapter to read resources straight from the source system.
 * It has either a {@link #filter} or an {@link #id}, never both, and no {@link #value}. The adapter answers
 * with the resources in {@link ResponseFintEvent#getValues()}.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestFintEvent implements FintEvent {
    /**
     * GUID for correlation ID. The same ID should follow the request both upstream and downstream.
     */
    @NotBlank
    private String corrId;

    /**
     * OrgId for the current customer.
     */
    @NotBlank
    private String orgId;

    /**
     * Name of the FINT domain. E.g. utdanning.
     */
    @NotBlank
    private String domainName;

    /**
     * Name of the FINT package. E.g. vurdering.
     */
    @NotBlank
    private String packageName;

    /**
     * Name of the FINT class/entity. E.g. fravar
     */
    @NotBlank
    private String resourceName;

    /**
     * The type of operation to be performed. See {@link OperationType}.
     */
    @NotNull
    private OperationType operationType;

    /**
     * When the event was created.
     */
    @Deprecated
    private long created;

    /**
     * How long the request is valid in milliseconds
     */
    private long timeToLive;

    /**
     * The object to which the event applies. Empty on {@link OperationType#READ}.
     */
    private String value;

    /**
     * The OData filter the resources must match. Only set on {@link OperationType#READ}, and never together
     * with {@link #id}. E.g. <code>systemId/identifikatorverdi eq '12345'</code>
     */
    private String filter;

    /**
     * The one resource to read. Only set on {@link OperationType#READ}, and never together with {@link #filter}.
     */
    @Valid
    private EventIdentifikator id;

    /**
     * The highest number of resources the answer to a {@link OperationType#READ} may hold. An adapter that
     * finds more answers with {@link ResponseFintEvent#isRejected()} and a reason, so the client is never
     * given a result where resources are missing.
     */
    @Min(1)
    private Integer maxResults;

    /**
     * Kept so code built against versions without {@link #filter}, {@link #id} and {@link #maxResults} still works.
     */
    public RequestFintEvent(String corrId, String orgId, String domainName, String packageName, String resourceName,
                            OperationType operationType, long created, long timeToLive, String value) {
        this(corrId, orgId, domainName, packageName, resourceName, operationType, created, timeToLive, value,
                null, null, null);
    }

}
