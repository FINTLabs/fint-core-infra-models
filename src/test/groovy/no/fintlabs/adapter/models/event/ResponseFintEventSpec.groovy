package no.fintlabs.adapter.models.event

import com.fasterxml.jackson.databind.ObjectMapper
import no.fintlabs.adapter.models.sync.SyncPageEntry
import no.fintlabs.adapter.operation.OperationType
import spock.lang.Specification

class ResponseFintEventSpec extends Specification {

    def mapper = new ObjectMapper()

    def "A read answer keeps the resources it found"() {
        when:
        def response = mapper.readValue('''{
            "corrId": "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
            "orgId": "fintlabs.no",
            "operationType": "READ",
            "values": [
                { "identifier": "12345", "resource": { "systemId": { "identifikatorverdi": "12345" } } },
                { "identifier": "67890", "resource": { "systemId": { "identifikatorverdi": "67890" } } }
            ]
        }''', ResponseFintEvent)

        then:
        response.operationType == OperationType.READ
        response.values*.identifier == ["12345", "67890"]
        response.values[0].resource == [systemId: [identifikatorverdi: "12345"]]
        response.value == null
        mapper.readValue(mapper.writeValueAsString(response), ResponseFintEvent) == response
    }

    def "A read answer that found nothing has an empty list"() {
        when:
        def response = mapper.readValue('''{
            "corrId": "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
            "orgId": "fintlabs.no",
            "operationType": "READ",
            "values": []
        }''', ResponseFintEvent)

        then:
        response.values == []
        !response.failed
        !response.rejected
    }

    def "An answer without values gets an empty list"() {
        expect:
        mapper.readValue('''{
            "corrId": "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
            "orgId": "fintlabs.no",
            "operationType": "CREATE",
            "value": { "identifier": "12345", "resource": {} }
        }''', ResponseFintEvent).values == []
        new ResponseFintEvent().values == []
        ResponseFintEvent.builder().build().values == []
    }

    def "The constructor from before values still works"() {
        given:
        def created = SyncPageEntry.of("12345", [systemId: [identifikatorverdi: "12345"]])

        when:
        def response = new ResponseFintEvent(
                "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
                "fintlabs.no",
                "https://visma.com/fintlabs.no/utdanning",
                1791278657000L,
                created,
                OperationType.CREATE,
                false,
                null,
                true,
                "The pupil is not in this school",
                false,
                null
        )

        then:
        response.value == created
        response.values == []
        response.operationType == OperationType.CREATE
        response.rejected
        response.rejectReason == "The pupil is not in this school"
        !response.failed
        !response.conflicted
    }
}
