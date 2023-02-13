package no.fintlabs.adapter.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a page sent from the adapter to the provider.
 * @param <T> The FINT resource type for the page.
 */
@Data
@AllArgsConstructor
@Builder
public class SyncPage<T> {
    /**
     * Metadata describing the page.
     */
    private SyncPageMetadata metadata;
    /**
     * Data in the page.
     */
    private List<SyncPageEntry<T>> resources;

    /**
     * Type of sync.
     */
    private final SyncType syncType;

    public SyncPage(SyncType syncType) {
        this.syncType = syncType;
    }

}
