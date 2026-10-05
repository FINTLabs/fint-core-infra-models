package no.fintlabs.adapter.models.v2.event;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Points at one resource by one of its identifier fields.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventIdentifier {
    /**
     * Name of the identifier field, in lower case. E.g. systemid or fodselsnummer.
     */
    @NotBlank
    private String field;

    /**
     * The value of the identifier field.
     */
    @NotBlank
    private String value;
}
