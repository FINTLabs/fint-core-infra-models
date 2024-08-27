package no.fintlabs.adapter.models.sync;

import no.fintlabs.adapter.models.sync.SyncPage;
import no.fintlabs.adapter.models.sync.SyncType;

/**
 * Represents a delta sync page.
 */
public class DeltaSyncPage extends SyncPage {
    public DeltaSyncPage() {
        super(SyncType.DELTA);
    }
}
