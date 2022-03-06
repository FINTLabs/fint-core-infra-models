package no.fintlabs.adapter.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represent the contract between the adapter and FINT.
 */
@Data
@Builder
public class AdapterContract {
    /**
     * <p>
     * A <strong>persistent uniq id</strong> for the adapter. It should be in the format:
     * </p>
     * <p>
     * <code>https://&lt;company domain&gt;/&lt;customer domain&gt;/&lt;FINT domain&gt;/&lt;FINT package (optional)&gt;/&lt;UUID (optional)&gt;</code>
     * </p>
     * <p>
     * E.g.
     *  <ul>
     *      <li><code>https://visma.com/rogfk.no/utdanning</code></li>
     *      <li><code>https://visma.com/rogfk.no/utdanning/elev</code></li>
     *      <li><code>https://visma.com/rogfk.no/utdanning/elev/b93ffb1e-2b86-4709-aa5d-03453152d9a4</code></li>
     *  </ul>
     * </p>
     */
    private String adapterId;
    /**
     * <p>
     * OrgId for the customer this contract represent.
     * </p>
     * <p>
     * E.g. <code>rogfk.no</code>
     * </p>
     */
    private String orgId;
    /**
     * <p>
     * Username used for authentication to FINT.
     * </p>
     * <p>
     * E.g. `vis@adapter.rogfk.no`
     * </p>
     */
    private String username;
    /**
     * Interval the adapter should ping FINT in minutes.
     */
    private long pingIntervalInMinutes;
    /**
     * <p>
     * List of capablilities for the adapter.
     * </p>
     * @see AdapterCapability
     */
    private List<AdapterCapability> capabilities;
    /**
     * The registration time for the contract in Unix timestamp.
     */
    private long time;

    /**
     * Represents the adapters capabilities
     */
    @Data
    @Builder
    public static class AdapterCapability {
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
         * @see DeltaSyncInterval
         */
        private DeltaSyncInterval deltaSyncInterval;

        /**
         * Helper method to generate the entity uri.
         * @return Returns the entity uri. E.g. /utdanning/elev/elev
         */
        public String getEntityUri() {
            return String.format("/%s/%s/%s", domain, packageName, clazz);
        }

        /**
         * Helper method to generate the component name.
         * @return Returns the component name. E.g. utdanning-elev
         */
        public String getComponent() {
            return String.format("%s-%s", domain, packageName);
        }

        public enum DeltaSyncInterval {
            /**
             * This indicates that the adapter will send updates as soon as they are availiable in the application.
             */
            IMMEDIATE,
            /**
             * This indicates that the adapter will send updates every <=15 minutes.
             */
            LEGACY
        }
    }
}
