package no.fintlabs.adapter.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents the adapters capabilities
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdapterCapability {
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
     * Number of days between each full sync
     */
    @Min(1)
    @Max(7)
    private int fullSyncIntervalInDays;
    /**
     * Delta sync strategy.
     *
     * @see DeltaSyncInterval
     */
    @NotNull
    private DeltaSyncInterval deltaSyncInterval;

    /**
     * Helper method to generate the entity uri.
     *
     * @return Returns the entity uri. E.g. /utdanning/elev/elev
     */
    public String getEntityUri() {
        return String.format("/%s/%s/%s", domainName, packageName, resourceName);
    }

    /**
     * Helper method to generate the component name.
     *
     * @return Returns the component name. E.g. utdanning-elev
     */
    public String getComponent() {
        return String.format("%s-%s", domainName, packageName);
    }

    /**
     * Represents delta sync strategies.
     */
    public enum DeltaSyncInterval {
        /**
         * Updates are dispatched immediately as they become available.
         */
        IMMEDIATE,
        /**
         * Updates are dispatched at intervals of up to <code>&lt;=15</code> minutes.
         */
        LEGACY,
        /**
         * No incremental updates are dispatched, only full synchronizations.
         */
        NONE
    }
}