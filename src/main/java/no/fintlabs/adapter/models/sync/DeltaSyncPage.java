package no.fintlabs.adapter.models.sync;

import java.util.List;

public class DeltaSyncPage extends SyncPage {
    public DeltaSyncPage(SyncPageMetadata metadata, List<SyncPageEntry> resources) {
        super(metadata, resources, SyncType.DELTA);
    }
}
