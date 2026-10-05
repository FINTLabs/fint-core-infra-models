package no.fintlabs.adapter.models.v2.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fintlabs.adapter.models.sync.SyncPageEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter's answer to an {@link EventRequest}.
 * <p>
 * How many resources the answer carries depends on the operation and the status:
 * </p>
 * <table>
 *     <caption>Number of resources</caption>
 *     <tr><th>Operation</th><th>SUCCEEDED</th><th>CONFLICT</th><th>REJECTED / ERROR</th></tr>
 *     <tr><td>READ with filter</td><td>0 to maxResults</td><td>-</td><td>0</td></tr>
 *     <tr><td>READ by id</td><td>0 or 1</td><td>-</td><td>0</td></tr>
 *     <tr><td>CREATE / UPDATE</td><td>exactly 1</td><td>exactly 1</td><td>0</td></tr>
 *     <tr><td>VALIDATE</td><td>0</td><td>exactly 1</td><td>0</td></tr>
 * </table>
 * <p>
 * A read by id that finds nothing answers {@link EventStatus#SUCCEEDED} with no resources,
 * and the client gets a 404.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    /**
     * The corrId from the {@link EventRequest} this answers.
     */
    @NotBlank
    private String corrId;

    /**
     * OrgId for the current customer.
     */
    @NotBlank
    private String orgId;

    /**
     * How the adapter handled the event.
     */
    @NotNull
    private EventStatus status;

    /**
     * Why the event was rejected, had a conflict or failed. Shown to the client.
     */
    private String message;

    /**
     * The resources the event produced. See the table on {@link EventResponse} for how many.
     */
    @Valid
    @NotNull
    @Builder.Default
    private List<SyncPageEntry> resources = new ArrayList<>();
}
