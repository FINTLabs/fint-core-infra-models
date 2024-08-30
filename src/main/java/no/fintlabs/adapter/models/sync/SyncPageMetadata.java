package no.fintlabs.adapter.models.sync;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fintlabs.adapter.models.AdapterCapability;
import no.fintlabs.adapter.models.AdapterContract;

import java.util.UUID;

/**
 * Represents metadata for a page in a sync.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncPageMetadata {
    /**
     * See {@link AdapterContract#getAdapterId()}
     */
    private String adapterId;
    /**
     * An uniq id for a sync. It is used to correlate all pages in a sync. It should be a {@link UUID#randomUUID() UUID} in
     * lowercase.
     */
    private String corrId;
    /**
     * See {@link AdapterContract#getOrgId()}
     */
    private String orgId;
    /**
     * The total amount of resources for all pages in a sync.
     */
    private long totalSize;
    /**
     * Current page in a sync.
     */
    private long page;
    /**
     * Size of current page.
     */
    private long pageSize;
    /**
     * Total pages in a sync.
     */
    private long totalPages;
    /**
     * The path for the FINT resource in a sync. E.g. <code>/utdanning/elev/fravar</code>.
     *
     * @see AdapterCapability#getEntityUri()
     */
    private String uriRef;
    /**
     * Unix timestamp for the current page in a sync. Each page should have a timestamp for when it was sent.
     *
     * @see System#currentTimeMillis()
     */
    private long time;

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