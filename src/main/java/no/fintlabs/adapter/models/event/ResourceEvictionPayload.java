package no.fintlabs.adapter.models.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResourceEvictionPayload {
    private final String domain;
    private final String pkg;
    private final String resource;
    private final String org;
    private final Long unixTimestamp;

    @JsonCreator
    public ResourceEvictionPayload(
            @JsonProperty("domain") String domain,
            @JsonProperty("pkg") String pkg,
            @JsonProperty("resource") String resource,
            @JsonProperty("org") String org,
            @JsonProperty("unixTimestamp") Long unixTimestamp
    ) {
        this.domain = domain;
        this.pkg = pkg;
        this.resource = resource;
        this.org = org;
        this.unixTimestamp = unixTimestamp;
    }
}
