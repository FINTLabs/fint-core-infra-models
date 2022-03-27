package no.fintlabs.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullSyncPage<T> {

    private Metadata metadata;
    private List<T> resources;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata {
        private String corrId;
        private String orgId;
        private long totalSize;
        private long page;
        private long totalPages;
        private String uriRef;
    }
}