package no.fintlabs.adapter.models.sync;

import no.fintlabs.adapter.models.sync.SyncPage;
import no.fintlabs.adapter.models.sync.SyncType;

/**
 * Represents a full sync page.
 */
public class FullSyncPage extends SyncPage {
    public FullSyncPage() {
        super(SyncType.FULL);
    }
}
