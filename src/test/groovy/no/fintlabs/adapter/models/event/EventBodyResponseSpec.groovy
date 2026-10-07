package no.fintlabs.adapter.models.event

import spock.lang.Specification

class EventBodyResponseSpec extends Specification {

    def "A rejected answer keeps its reject reason as the message"() {
        given:
        def response = ResponseFintEvent.builder()
                .rejected(true)
                .rejectReason("More than 1000 resources match, use a narrower filter")
                .build()

        when:
        def body = EventBodyResponse.ofResponseEvent(response)

        then:
        body.message == "More than 1000 resources match, use a narrower filter"
        body.statusCode == "REJECTED"
        body.responseStatus == ResponseStatus.REJECTED
    }

    def "A failed answer keeps its error message as the message"() {
        given:
        def response = ResponseFintEvent.builder()
                .failed(true)
                .errorMessage("The source system did not answer")
                .build()

        when:
        def body = EventBodyResponse.ofResponseEvent(response)

        then:
        body.message == "The source system did not answer"
        body.statusCode == "ERROR"
        body.responseStatus == ResponseStatus.ERROR
    }

    def "A conflicted answer keeps its conflict reason as the message"() {
        given:
        def response = ResponseFintEvent.builder()
                .conflicted(true)
                .conflictReason("The resource was changed in the source system")
                .build()

        when:
        def body = EventBodyResponse.ofResponseEvent(response)

        then:
        body.message == "The resource was changed in the source system"
        body.statusCode == "CONFLICT"
        body.responseStatus == ResponseStatus.CONFLICT
    }

    def "A failed answer without an error message gets an empty message"() {
        when:
        def body = EventBodyResponse.ofResponseEvent(ResponseFintEvent.builder().failed(true).build())

        then:
        body.message == ""
        body.statusCode == "ERROR"
    }

    def "An answer that went well gets an empty message"() {
        when:
        def body = EventBodyResponse.ofResponseEvent(ResponseFintEvent.builder().build())

        then:
        body.message == ""
        body.statusCode == ""
        body.responseStatus == ResponseStatus.ACCEPTED
    }
}
