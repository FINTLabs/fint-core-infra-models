package no.fintlabs.adapter.models.sync;

import java.util.List;

public class FullSyncPage extends SyncPage {
    public FullSyncPage(SyncPageMetadata metadata, List<SyncPageEntry> resources) {
        super(metadata, resources, SyncType.FULL);
    }
}
