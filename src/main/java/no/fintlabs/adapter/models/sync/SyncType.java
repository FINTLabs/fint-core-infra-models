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

    public static SyncType fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Sync type cannot be null or empty");
        }
        String normalized = value.trim().toLowerCase();

        switch (normalized) {
            case "full":
                return FULL;
            case "delta":
                return DELTA;
            case "delete":
                return DELETE;
            default:
                throw new IllegalArgumentException("Unknown sync type: " + value);
        }
    }

}
