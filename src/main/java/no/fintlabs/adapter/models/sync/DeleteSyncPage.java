package no.fintlabs.adapter.models.sync;

import no.fintlabs.adapter.models.sync.SyncPage;
import no.fintlabs.adapter.models.sync.SyncType;

/**
 * Represents a delete sync page.
 */
public class DeleteSyncPage extends SyncPage {
    public DeleteSyncPage() {
        super(SyncType.DELETE);
    }
}
