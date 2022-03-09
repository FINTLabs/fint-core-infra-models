package no.fintlabs.adapter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String domain;
    /**
     * Name of the FINT package. E.g. vurdering.
     */
    @JsonProperty("package")
    private String packageName;
    /**
     * Name of the FINT class/entity. E.g. fravar
     */
    @JsonProperty("class")
    private String clazz;
    /**
     * Number of days between each full sync
     */
    private int fullSyncIntervalInDays;
    /**
     * Delta sync strategy.
     *
     * @see DeltaSyncInterval
     */
    private DeltaSyncInterval deltaSyncInterval;

    /**
     * Helper method to generate the entity uri.
     *
     * @return Returns the entity uri. E.g. /utdanning/elev/elev
     */
    public String getEntityUri() {
        return String.format("/%s/%s/%s", domain, packageName, clazz);
    }

    /**
     * Helper method to generate the component name.
     *
     * @return Returns the component name. E.g. utdanning-elev
     */
    public String getComponent() {
        return String.format("%s-%s", domain, packageName);
    }

    /**
     * Represents delta sync strategies.
     */
    public enum DeltaSyncInterval {
        /**
         * This indicates that the adapter will send updates as soon as they are available in the application.
         */
        IMMEDIATE,
        /**
         * This indicates that the adapter will send updates every <code><=15</code> minutes.
         */
        LEGACY
    }
}