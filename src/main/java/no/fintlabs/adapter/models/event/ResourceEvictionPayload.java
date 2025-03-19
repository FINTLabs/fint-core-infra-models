package no.fintlabs.adapter.models.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceEvictionPayload {
    private final String domain;
    private final String pkg;
    private final String resource;
    private final String org;
    private final Long unixTimestamp;
}
