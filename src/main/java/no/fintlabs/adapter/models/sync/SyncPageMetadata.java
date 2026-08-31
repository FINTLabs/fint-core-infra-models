package no.fintlabs.adapter.models.sync;

import jakarta.validation.constraints.NotBlank;
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
    @Deprecated
    private String adapterId;
    /**
     * An uniq id for a sync. It is used to correlate all pages in a sync. It should be a {@link UUID#randomUUID() UUID} in
     * lowercase.
     */
    @NotBlank
    private String corrId;
    /**
     * See {@link AdapterContract#getOrgId()}
     */
    @NotBlank
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
    @Deprecated
    private String uriRef;
    /**
     * Unix timestamp for the current page in a sync. Each page should have a timestamp for when it was sent.
     *
     * @see System#currentTimeMillis()
     */
    @Deprecated
    private long time;

}
