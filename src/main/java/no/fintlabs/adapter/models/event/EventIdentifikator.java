package no.fintlabs.adapter.models.event;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventIdentifikator {

    private final String idField;
    private final String idValue;

}
