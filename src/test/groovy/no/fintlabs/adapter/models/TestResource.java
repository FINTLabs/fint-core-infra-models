package no.fintlabs.adapter.models;

import no.fint.model.resource.FintLinks;
import no.fint.model.resource.Link;

import java.util.List;
import java.util.Map;

public class TestResource implements FintLinks {
    private final Map<String, List<Link>> links = this.createLinks();

    @Override
    public Map<String, List<Link>> getLinks() {
        return links;
    }
}
