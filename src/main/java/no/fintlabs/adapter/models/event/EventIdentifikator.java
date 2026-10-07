package no.fintlabs.adapter.models.event;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Points at one resource by the name of an identifier field and the value it has.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventIdentifikator {

    /**
     * Name of the identifier field in lowercase, as in the path of the resource. E.g. systemid
     */
    @NotBlank
    private String idField;

    /**
     * The value of the identifier. E.g. 12345
     */
    @NotBlank
    private String idValue;

}
