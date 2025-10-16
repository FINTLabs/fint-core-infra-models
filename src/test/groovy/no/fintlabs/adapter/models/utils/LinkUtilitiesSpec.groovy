package no.fintlabs.adapter.models.utils

import no.fint.model.resource.Link
import no.fintlabs.adapter.models.TestResource
import spock.lang.Specification

class LinkUtilitiesSpec extends Specification {

    def testResource

    void setup() {
        testResource = new TestResource()
        testResource.addSelf(Link.with(TestResource.class, "systemid", "12345"))
        testResource.addSelf(Link.with(TestResource.class, "testIdentifier", "54321"))
    }

    def "getSelfLinkBy should return the raw identifier"() {
        when:
        def test = LinkUtilities.getSelfLinkBy("testIdentifier", testResource)

        then:
        test.equals("54321")
    }

    def "getSelfLinkBy should thrown and exception if the identifier don't exist"() {
        when:
        LinkUtilities.getSelfLinkBy("invalidIdentifier", testResource)

        then:
        thrown(IllegalArgumentException)
    }

    def "getSelfLinkBySystemId should return the identifier for systemid"() {

        when:
        def systemid = LinkUtilities.getSelfLinkBySystemId(testResource)

        then:
        systemid.equals("12345")
    }
}
