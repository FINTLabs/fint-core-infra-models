package no.fintlabs.adapter.models

import spock.lang.Specification

class AdapterContractSpec extends Specification {

    def "Get entity uri should return domain, package and class in the format /domain/package/class"() {

        given:
        def adapterCapability = AdapterCapability.builder()
                .domain("utdanning")
                .packageName("elev")
                .clazz("skoleressurs")
                .build()

        when:
        def entityUri = adapterCapability.getEntityUri()

        then:
        entityUri == "/utdanning/elev/skoleressurs"
    }

    def "Get component should return domain and package in the format domain-package"() {
        given:
        def adapterCapability = AdapterCapability.builder()
                .domain("utdanning")
                .packageName("elev")
                .clazz("skoleressurs")
                .build()
        when:
        def component = adapterCapability.getComponent()

        then:
        component == "utdanning-elev"
    }
}
