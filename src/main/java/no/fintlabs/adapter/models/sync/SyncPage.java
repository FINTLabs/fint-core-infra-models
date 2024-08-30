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

}
