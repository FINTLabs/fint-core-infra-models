package no.fintlabs.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeltaSyncEntity<T> {

    private Metadata metadata;
    private List<HashMap<String, T>> resources;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Metadata {
        private String orgId;
        private long totalSize;
    }
}
