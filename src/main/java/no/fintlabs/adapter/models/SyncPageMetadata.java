package no.fintlabs.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncPageMetadata {
    private String adapterId;
    private String corrId;
    private String orgId;
    private long totalSize;
    private long page;
    private long pageSize;
    private long totalPages;
    private String uriRef;
    private long time;
}