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
     * The id of the adapter that sent the page, see {@link AdapterContract#getAdapterId()}. Set by FINT from
     * the adapter's contract when it receives the page. A value sent by the adapter is ignored.
     */
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
     * The path for the FINT resource in a sync. E.g. <code>/utdanning/elev/fravar</code>. Set by FINT from the
     * path the page was sent to. A value sent by the adapter is ignored.
     *
     * @see AdapterCapability#getEntityUri()
     */
    private String uriRef;
    /**
     * When FINT received the page, as a Unix timestamp in milliseconds. Set by FINT. A value sent by the
     * adapter is ignored.
     */
    private long time;

}
