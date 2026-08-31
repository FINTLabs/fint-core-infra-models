package no.fintlabs.adapter.models.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fintlabs.adapter.operation.OperationType;

/**
 * Represents a request to the adapter
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
     * The type of operation to be performed (CREATE, UPDATE, VALIDATE)
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
     * The object to which the event applies
     */
    private String value;

}
