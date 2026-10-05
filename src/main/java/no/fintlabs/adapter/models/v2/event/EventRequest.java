package no.fintlabs.adapter.models.v2.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request from FINT to the adapter.
 * <p>
 * A {@link EventOperation#READ} request carries either a {@link #filter} or an {@link #id}, never both.
 * A read with neither would mean "fetch everything", which is what full sync is for.
 * Write operations carry the resource in {@link #value}.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    /**
     * GUID for correlation ID. The same ID must be used in the {@link EventResponse}.
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
     * The operation the adapter is asked to perform.
     */
    @NotNull
    private EventOperation operation;

    /**
     * When the event was created, in epoch milliseconds.
     */
    private long created;

    /**
     * The point in time, in epoch milliseconds, after which an answer is no longer accepted.
     */
    private long deadline;

    /**
     * The resource as JSON, for {@link EventOperation#CREATE}, {@link EventOperation#UPDATE}
     * and {@link EventOperation#VALIDATE}. Empty on reads.
     */
    private String value;

    /**
     * An OData $filter expression, only on {@link EventOperation#READ}. E.g. navn/fornavn eq 'Ola'.
     * The filter has already been checked for valid syntax before it reaches the adapter.
     */
    private String filter;

    /**
     * The one resource to read, only on {@link EventOperation#READ}.
     */
    @Valid
    private EventIdentifier id;

    /**
     * The most resources a {@link EventOperation#READ} answer may carry. Empty on write operations.
     * <p>
     * Read results are stored on the event itself, which has a 16 MB limit. They are also sent to
     * FINT in one request that has to finish within 120 seconds, and returned to the client in one
     * response without paging. The cap keeps all three well inside their limits. Large amounts of
     * data belong in full sync, not in a read event.
     * </p>
     * <p>
     * If the source system finds more resources than this, answer with {@link EventStatus#REJECTED}
     * and a message asking the client to narrow the filter. Asking the source system for
     * maxResults + 1 resources is enough to find out.
     * </p>
     */
    private Integer maxResults;
}
