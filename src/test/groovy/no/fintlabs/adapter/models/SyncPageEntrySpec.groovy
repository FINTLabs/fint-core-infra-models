package no.fintlabs.adapter.models


import no.fint.model.resource.Link
import no.fintlabs.adapter.models.sync.SyncPageEntry
import spock.lang.Specification

class SyncPageEntrySpec extends Specification {

    def testResource

    void setup() {
        testResource = new TestResource()
        testResource.addSelf(Link.with(TestResource.class, "systemid", "12345"))
        testResource.addSelf(Link.with(TestResource.class, "testIdentifier", "54321"))
    }

    def "Creating a SyncPageEntry should have a identifier and resource equals the parameters"() {
        when:
        def entry = SyncPageEntry.of("/test/1234", testResource)

        then:
        entry.getIdentifier() == "/test/1234"
        entry.getResource()
    }

    def "Creating a SyncPageEntry from systemid should have a systemid identifier"() {
        when:
        def entry = SyncPageEntry.ofSystemId(testResource)

        then:
        entry.getIdentifier().equals("12345")
    }

    def "Creating a SyncPageEntry from identifier name should have raw identifier"() {
        when:
        def entry = SyncPageEntry.ofIdentifierName("testIdentifier", testResource)

        then:
        entry.getIdentifier().equals("54321")
    }
}
