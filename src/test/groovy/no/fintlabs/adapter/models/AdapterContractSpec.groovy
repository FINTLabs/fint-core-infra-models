package no.fintlabs.adapter.models

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Validation
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import spock.lang.Specification

class AdapterContractSpec extends Specification {

    def "A contract allows a null heartbeat interval through its construction APIs"() {
        expect:
        new AdapterContract().heartbeatIntervalInMinutes == null
        AdapterContract.builder().build().heartbeatIntervalInMinutes == null
        AdapterContract.builder().heartbeatIntervalInMinutes(null).build().heartbeatIntervalInMinutes == null
        new AdapterContract("id", "org", "user", null, [] as Set, 0L).heartbeatIntervalInMinutes == null
        new AdapterContract("id", "org", "user", null, [] as Set, 0L, [] as Set).heartbeatIntervalInMinutes == null

        when:
        def contract = AdapterContract.builder().heartbeatIntervalInMinutes(2).build()
        contract.setHeartbeatIntervalInMinutes(null)

        then:
        contract.heartbeatIntervalInMinutes == null
    }

    def "A contract reads heartbeat intervals from JSON"() {
        expect:
        new ObjectMapper().readValue(json, AdapterContract).heartbeatIntervalInMinutes == expected

        where:
        json                                  | expected
        '{}'                                  | null
        '{"heartbeatIntervalInMinutes":null}'   | null
        '{"heartbeatIntervalInMinutes":2}'      | 2
    }

    def "Heartbeat interval validation allows null and values from one to five"() {
        given:
        def factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
        def contract = new AdapterContract("id", "org", "user", interval, [] as Set, 0L)

        expect:
        factory.validator.validate(contract).isEmpty() == valid

        cleanup:
        factory.close()

        where:
        interval | valid
        null     | true
        1        | true
        2        | true
        5        | true
        0        | false
        6        | false
    }

    def "A contract reads time from JSON"() {
        expect:
        new ObjectMapper().readValue(json, AdapterContract).time == expected

        where:
        json            | expected
        '{}'            | null
        '{"time":null}' | null
        '{"time":5}'    | 5L
    }

    def "A contract reads missing or null event capabilities from JSON as an empty set"() {
        expect:
        new ObjectMapper().readValue(json, AdapterContract).eventCapabilities == [] as Set

        where:
        json << ['{}', '{"eventCapabilities":null}']
    }

    def "Setting event capabilities to null leaves an empty set"() {
        given:
        def contract = new AdapterContract()

        when:
        contract.setEventCapabilities(null)

        then:
        contract.eventCapabilities == [] as Set
    }

    def "Get entity uri should return domain, package and class in the format /domain/package/class"() {

        given:
        def adapterCapability = AdapterCapability.builder()
                .domainName("utdanning")
                .packageName("elev")
                .resourceName("skoleressurs")
                .build()

        when:
        def entityUri = adapterCapability.getEntityUri()

        then:
        entityUri == "/utdanning/elev/skoleressurs"
    }

    def "Get component should return domain and package in the format domain-package"() {
        given:
        def adapterCapability = AdapterCapability.builder()
                .domainName("utdanning")
                .packageName("elev")
                .resourceName("skoleressurs")
                .build()
        when:
        def component = adapterCapability.getComponent()

        then:
        component == "utdanning-elev"
    }
}
