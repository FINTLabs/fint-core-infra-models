package no.fintlabs.adapter.models.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fintlabs.adapter.models.AdapterContract;
import no.fintlabs.adapter.models.sync.SyncPageEntry;
import no.fintlabs.adapter.operation.OperationType;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter's answer to a {@link RequestFintEvent}.
 * <p>
 * A {@link OperationType#READ} is answered with the resources in {@link #values}. The other operations
 * answer with one resource in {@link #value}.
 * </p>
 * <p>
 * An adapter that cannot do what the event asks sets one of {@link #failed}, {@link #rejected} and
 * {@link #conflicted}, together with the message that belongs to it.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseFintEvent implements FintEvent {
    /**
     * GUID for correlation ID. The same ID should follow the request both upstream and downstream.
     */
    @NotBlank
    private String corrId;

    /**
     * OrgId for the current customer.
     */
    @NotBlank
    private String orgId;

    /**
     * See {@link AdapterContract#getAdapterId()}
     */
    @Deprecated
    private String adapterId;

    /**
     * When the event was handled.
     */
    @Deprecated
    private long handledAt;

    /**
     * The SyncPageEntry of the object that the event produced. The object should be a FINT resource.
     * Not used on {@link OperationType#READ}, which answers in {@link #values}.
     */
    private SyncPageEntry value;

    /**
     * The resources a {@link OperationType#READ} found, in the same form as on sync. An empty list means
     * that nothing was found. A read by id holds at most one resource.
     */
    @NotNull
    @Valid
    @Builder.Default
    private List<SyncPageEntry> values = new ArrayList<>();

    /**
     * The operation of the request this event answers.
     */
    @NotNull
    private OperationType operationType;

    /**
     * True when the adapter could not handle the event because something broke, for example that the
     * source system is down.
     */
    private boolean failed;

    /**
     * A message that explains the reason for the failure of the event.
     */
    private String errorMessage;

    /**
     * True when the adapter cannot do what the event asks, for example a filter it cannot translate, or a
     * {@link OperationType#READ} that finds more than {@link RequestFintEvent#getMaxResults()} resources.
     */
    private boolean rejected;

    /**
     * A message that explains the reason for the rejection of the event.
     */
    private String rejectReason;

    /**
     * True when a write conflicts with what the source system already holds. Not used on
     * {@link OperationType#READ}.
     */
    private boolean conflicted;

    /**
     * A message that explains the reason for the conflict of the event.
     */
    private String conflictReason;

    /**
     * Kept so code built against versions without {@link #values} still works.
     */
    public ResponseFintEvent(String corrId, String orgId, String adapterId, long handledAt, SyncPageEntry value,
                             OperationType operationType, boolean failed, String errorMessage, boolean rejected,
                             String rejectReason, boolean conflicted, String conflictReason) {
        this(corrId, orgId, adapterId, handledAt, value, new ArrayList<>(), operationType, failed, errorMessage,
                rejected, rejectReason, conflicted, conflictReason);
    }

}
