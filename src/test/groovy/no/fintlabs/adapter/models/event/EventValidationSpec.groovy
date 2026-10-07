package no.fintlabs.adapter.models.event

import jakarta.validation.Validation
import no.fintlabs.adapter.models.EventCapability
import no.fintlabs.adapter.models.sync.SyncPageEntry
import no.fintlabs.adapter.operation.OperationType
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import spock.lang.Specification

class EventValidationSpec extends Specification {

    def validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .validator

    def "An event capability with operations is valid"() {
        expect:
        invalidFields(new EventCapability("utdanning", "vurdering", "elevfravar", [OperationType.READ] as Set)) == []
    }

    def "An event capability without operations is not valid"() {
        expect:
        invalidFields(new EventCapability("utdanning", "vurdering", "elevfravar", [] as Set)) == ["operations"]
    }

    def "A read request by id is not valid when the id has no value"() {
        given:
        def request = readRequest()
        request.id = new EventIdentifikator("systemid", "")

        expect:
        invalidFields(request) == ["id.idValue"]
    }

    def "A read answer is not valid when a resource has no identifier"() {
        given:
        def response = ResponseFintEvent.builder()
                .corrId("0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11")
                .orgId("fintlabs.no")
                .operationType(OperationType.READ)
                .values([SyncPageEntry.of("12345", [:]), SyncPageEntry.of("", [:])])
                .build()

        expect:
        invalidFields(response) == ["values[1].identifier"]
    }

    private RequestFintEvent readRequest() {
        RequestFintEvent.builder()
                .corrId("0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11")
                .orgId("fintlabs.no")
                .domainName("utdanning")
                .packageName("vurdering")
                .resourceName("elevfravar")
                .operationType(OperationType.READ)
                .build()
    }

    private List<String> invalidFields(Object target) {
        validator.validate(target).collect { it.propertyPath.toString() }.sort()
    }
}
