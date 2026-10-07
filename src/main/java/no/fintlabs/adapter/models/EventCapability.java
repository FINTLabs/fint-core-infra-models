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
import no.fintlabs.adapter.operation.OperationType;

import java.util.Set;

/**
 * A resource the adapter answers events for, and which operations it answers.
 * <p>
 * This is separate from {@link AdapterCapability}, which promises full syncs. A resource can be in
 * either list or in both.
 * </p>
 * <p>
 * FINT uses the list like this when the adapter asks for events:
 * </p>
 * <ul>
 *     <li>A resource in the list gets events for exactly the operations it lists. A resource listed with only
 *     {@link OperationType#READ} gets no create or update events.</li>
 *     <li>A resource that is not in the list gets events as before: every operation except
 *     {@link OperationType#READ}, as long as the adapter has the role for the component.</li>
 * </ul>
 * <p>
 * Read results are only returned on the event and never stored in the FINT cache. The result of a write
 * ({@link OperationType#CREATE}, {@link OperationType#UPDATE}) is stored in the cache, also for a resource
 * without full sync. A resource type that gets no completed full sync is removed from the cache after a
 * while, so on a resource without full sync, written resources only stay in the cache for a limited time.
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
    private Set<OperationType> operations;

    /**
     * Helper method to generate the entity uri.
     *
     * @return Returns the entity uri. E.g. /utdanning/elev/elev
     */
    public String getEntityUri() {
        return String.format("/%s/%s/%s", domainName, packageName, resourceName);
    }
}
