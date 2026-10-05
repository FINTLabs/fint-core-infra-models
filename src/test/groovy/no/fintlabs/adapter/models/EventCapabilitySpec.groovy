package no.fintlabs.adapter.models

import com.fasterxml.jackson.databind.ObjectMapper
import no.fintlabs.adapter.models.v2.event.EventOperation
import spock.lang.Specification

class EventCapabilitySpec extends Specification {

    def mapper = new ObjectMapper()

    def "A contract without eventCapabilities gets an empty set"() {
        when:
        def contract = mapper.readValue('''{
            "adapterId": "https://visma.com/rogfk.no/utdanning",
            "orgId": "rogfk.no",
            "username": "vis@adapter.rogfk.no",
            "heartbeatIntervalInMinutes": 2,
            "capabilities": []
        }''', AdapterContract)

        then:
        contract.eventCapabilities == [] as Set
        AdapterContract.builder().build().eventCapabilities == [] as Set
        new AdapterContract("id", "rogfk.no", "user", 2, [] as Set, 0L).eventCapabilities == [] as Set
    }

    def "A contract reads the event capabilities and their operations"() {
        when:
        def contract = mapper.readValue('''{
            "orgId": "rogfk.no",
            "capabilities": [],
            "eventCapabilities": [
                { "domainName": "utdanning", "packageName": "elev", "resourceName": "elev", "operations": ["READ"] },
                { "domainName": "utdanning", "packageName": "vurdering", "resourceName": "fravar", "operations": ["CREATE", "VALIDATE"] }
            ]
        }''', AdapterContract)

        then:
        contract.eventCapabilities.collectEntries { [(it.entityUri): it.operations] } == [
                "/utdanning/elev/elev"       : [EventOperation.READ] as Set,
                "/utdanning/vurdering/fravar": [EventOperation.CREATE, EventOperation.VALIDATE] as Set,
        ]
    }

    def "Two event capabilities with the same resource and operations are equal"() {
        expect:
        new EventCapability("utdanning", "elev", "elev", [EventOperation.READ] as Set) ==
                new EventCapability("utdanning", "elev", "elev", [EventOperation.READ] as Set)
    }
}
