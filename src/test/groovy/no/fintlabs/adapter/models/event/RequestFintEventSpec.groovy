package no.fintlabs.adapter.models.event

import com.fasterxml.jackson.databind.ObjectMapper
import no.fintlabs.adapter.operation.OperationType
import spock.lang.Specification

class RequestFintEventSpec extends Specification {

    def mapper = new ObjectMapper()

    def "A read request by filter keeps the filter and the max results"() {
        when:
        def request = mapper.readValue('''{
            "corrId": "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
            "orgId": "fintlabs.no",
            "domainName": "utdanning",
            "packageName": "vurdering",
            "resourceName": "elevfravar",
            "operationType": "READ",
            "created": 1791278657000,
            "timeToLive": 1791279557000,
            "filter": "systemId/identifikatorverdi eq '12345'",
            "maxResults": 1000
        }''', RequestFintEvent)

        then:
        request.operationType == OperationType.READ
        request.filter == "systemId/identifikatorverdi eq '12345'"
        request.maxResults == 1000
        request.id == null
        request.value == null
        mapper.readValue(mapper.writeValueAsString(request), RequestFintEvent) == request
    }

    def "A read request by id keeps the id field and the id value"() {
        when:
        def request = mapper.readValue('''{
            "corrId": "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
            "orgId": "fintlabs.no",
            "domainName": "utdanning",
            "packageName": "vurdering",
            "resourceName": "elevfravar",
            "operationType": "READ",
            "created": 1791278657000,
            "timeToLive": 1791279557000,
            "id": { "idField": "systemid", "idValue": "12345" },
            "maxResults": 1
        }''', RequestFintEvent)

        then:
        request.operationType == OperationType.READ
        request.id == new EventIdentifikator("systemid", "12345")
        request.maxResults == 1
        request.filter == null
        request.value == null
        mapper.readValue(mapper.writeValueAsString(request), RequestFintEvent) == request
    }

    def "A write request has no filter, no id and no max results"() {
        when:
        def request = mapper.readValue('''{
            "corrId": "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
            "orgId": "fintlabs.no",
            "domainName": "utdanning",
            "packageName": "vurdering",
            "resourceName": "elevfravar",
            "operationType": "CREATE",
            "created": 1791278657000,
            "timeToLive": 1791279557000,
            "value": "{}"
        }''', RequestFintEvent)

        then:
        request.value == "{}"
        request.filter == null
        request.id == null
        request.maxResults == null
    }

    def "The constructor from before the read fields still works"() {
        when:
        def request = new RequestFintEvent(
                "0f6c2a1e-6e0b-4c0e-9a57-2f4d1f7b8c11",
                "fintlabs.no",
                "utdanning",
                "vurdering",
                "elevfravar",
                OperationType.CREATE,
                1791278657000L,
                1791279557000L,
                "{}"
        )

        then:
        request.operationType == OperationType.CREATE
        request.value == "{}"
        request.filter == null
        request.id == null
        request.maxResults == null
    }
}
