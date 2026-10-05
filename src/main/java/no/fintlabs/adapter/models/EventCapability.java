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
 *     <li>Only in {@link AdapterContract#getCapabilities()}: the resource is kept in the FINT cache through full sync.</li>
 *     <li>Only in {@link AdapterContract#getEventCapabilities()}: the resource has no full sync. Clients can read it
 *     live through {@link EventOperation#READ} events, and can write to it if the adapter lists write operations.</li>
 *     <li>In both: the resource is kept in the cache through full sync, and the adapter also answers events for it.</li>
 * </ul>
 * <p>
 * Read results are only returned on the event and never stored in the FINT cache. The result of a write
 * ({@link EventOperation#CREATE}, {@link EventOperation#UPDATE}) is stored in the cache, also for a resource
 * without full sync. A resource type that gets no completed full sync is removed from the cache after a
 * while, so on a resource without full sync, written resources only stay in the cache for a limited time.
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
