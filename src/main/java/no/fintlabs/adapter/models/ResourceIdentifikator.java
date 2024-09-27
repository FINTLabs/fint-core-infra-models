package no.fintlabs.adapter.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResourceIdentifikator {

    private final String idField;
    private final String idValue;

}
