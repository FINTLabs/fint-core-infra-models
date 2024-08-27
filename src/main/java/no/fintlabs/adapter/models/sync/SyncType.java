package no.fintlabs.adapter.models.sync;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum SyncType {
    FULL(HttpMethod.POST),
    DELTA(HttpMethod.PATCH),
    DELETE(HttpMethod.DELETE);

    private final HttpMethod httpMethod;

    SyncType(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

}
