package no.fintlabs.adapter.models.sync;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represents a page sent from the adapter to the provider.
 */
@Data
@AllArgsConstructor
@Builder
public class SyncPage {

    /**
     * Metadata describing the page.
     */
    private SyncPageMetadata metadata;
    /**
     * Data in the page.
     */
    private List<SyncPageEntry> resources;
    /**
     * SyncType for the Syncpage.
     *
     * <p>This enum represents the different types of synchronization operations that can be performed.
     * Each type is associated with a specific {@link org.springframework.http.HttpMethod HttpMethod} to indicate
     * the type of HTTP request used for that operation.</p>
     *
     * <ul>
     *     <li>{@link SyncType#FULL FULL}: Used for sending full datasets. This will send the entire set of data to the server using {@link org.springframework.http.HttpMethod#POST POST}.</li>
     *     <li>{@link SyncType#DELTA DELTA}: Used for sending only updated or modified entries. This will send only the changes using {@link org.springframework.http.HttpMethod#PATCH PATCH}.</li>
     *     <li>{@link SyncType#DELETE DELETE}: Used for deleting entities. This will remove the specified entities using {@link org.springframework.http.HttpMethod#DELETE DELETE}.</li>
     * </ul>
     *
     */
    private SyncType syncType;

}
