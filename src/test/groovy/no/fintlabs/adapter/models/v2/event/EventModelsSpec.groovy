package no.fintlabs.adapter.models.v2.event

import com.fasterxml.jackson.databind.ObjectMapper
import no.fintlabs.adapter.models.AdapterCapability
import spock.lang.Specification

class EventModelsSpec extends Specification {

    def mapper = new ObjectMapper()

    def "A read request with a filter is read from JSON"() {
        when:
        def request = mapper.readValue('''{
            "corrId": "8f1c",
            "orgId": "afk.no",
            "domainName": "utdanning",
            "packageName": "elev",
            "resourceName": "elev",
            "operation": "READ",
            "created": 1759651200000,
            "deadline": 1759652100000,
            "filter": "navn/fornavn eq 'Ola'",
            "maxResults": 1000
        }''', EventRequest)

        then:
        request.operation == EventOperation.READ
        request.filter == "navn/fornavn eq 'Ola'"
        request.id == null
        request.maxResults == 1000
    }

    def "A read request by id is read from JSON"() {
        when:
        def request = mapper.readValue('''{
            "corrId": "8f1c",
            "operation": "READ",
            "id": { "field": "fodselsnummer", "value": "12345678901" }
        }''', EventRequest)

        then:
        request.id == new EventIdentifier("fodselsnummer", "12345678901")
        request.filter == null
    }

    def "A response without resources gets an empty list"() {
        expect:
        EventResponse.builder().corrId("8f1c").status(EventStatus.SUCCEEDED).build().resources == []
        mapper.readValue('{"corrId": "8f1c", "status": "REJECTED", "message": "Too many results"}', EventResponse).resources == []
    }

    def "A response with several resources is read from JSON"() {
        when:
        def response = mapper.readValue('''{
            "corrId": "8f1c",
            "orgId": "afk.no",
            "status": "SUCCEEDED",
            "resources": [
                { "identifier": "1", "resource": { "navn": "Ola" } },
                { "identifier": "2", "resource": { "navn": "Kari" } }
            ]
        }''', EventResponse)

        then:
        response.status == EventStatus.SUCCEEDED
        response.resources*.identifier == ["1", "2"]
    }

    def "A capability without readEvents does not support read events"() {
        when:
        def capability = mapper.readValue('''{
            "domainName": "utdanning",
            "packageName": "elev",
            "resourceName": "elev",
            "fullSyncIntervalInDays": 1,
            "deltaSyncInterval": "IMMEDIATE"
        }''', AdapterCapability)

        then:
        !capability.readEvents
        !AdapterCapability.builder().build().readEvents
        !new AdapterCapability("utdanning", "elev", "elev", 1, AdapterCapability.DeltaSyncInterval.IMMEDIATE).readEvents
    }

    def "A capability can turn on read events"() {
        expect:
        mapper.readValue('{"readEvents": true}', AdapterCapability).readEvents
    }
}
