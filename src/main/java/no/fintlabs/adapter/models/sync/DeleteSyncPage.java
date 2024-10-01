package no.fintlabs.adapter.models.sync;

import java.util.List;

public class DeleteSyncPage extends SyncPage {
    public DeleteSyncPage(SyncPageMetadata metadata, List<SyncPageEntry> resources) {
        super(metadata, resources, SyncType.DELETE);
    }
}
