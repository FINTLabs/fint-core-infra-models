package no.fintlabs.adapter.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import no.fintlabs.adapter.models.v2.event.EventOperation;

import java.util.Set;

/**
 * A resource the adapter answers events for, and which operations it answers.
 * <p>
 * This is separate from {@link AdapterCapability}, which promises full syncs. A resource can be in
 * either list or in both:
 * </p>
 * <ul>
 *     <li>Only in {@link AdapterContract#getCapabilities()}: the resource is cached in FINT through full sync.</li>
 *     <li>Only in {@link AdapterContract#getEventCapabilities()}: the resource is not cached, and clients
 *     can only read it live through {@link EventOperation#READ} events.</li>
 *     <li>In both: the resource is cached, and clients can also read it live.</li>
 * </ul>
 * <p>
 * Write operations ({@link EventOperation#CREATE}, {@link EventOperation#UPDATE},
 * {@link EventOperation#VALIDATE}, {@link EventOperation#DELETE}) are only allowed on resources that are
 * also in {@link AdapterContract#getCapabilities()}, because the result of a write is stored in the FINT cache.
 * </p>
 * <p>
 * Only the v2 event API checks these operations. The v1 event API sends events to any adapter that
 * has the role for the component.
 * </p>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventCapability {
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
     * The operations the adapter answers for this resource. E.g. [READ] or [CREATE, VALIDATE].
     */
    @NotEmpty
    private Set<EventOperation> operations;

    /**
     * Helper method to generate the entity uri.
     *
     * @return Returns the entity uri. E.g. /utdanning/elev/elev
     */
    public String getEntityUri() {
        return String.format("/%s/%s/%s", domainName, packageName, resourceName);
    }
}
