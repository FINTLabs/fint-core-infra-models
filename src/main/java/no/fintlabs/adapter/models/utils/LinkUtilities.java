package no.fintlabs.adapter.models.utils;

import no.fint.model.resource.FintLinks;

public class LinkUtilities {

    public static String getSelfLinkBy(String identifier, FintLinks resource) {
        return resource
                .getSelfLinks()
                .stream()
                .filter(link -> link.getHref().contains("/" + identifier + "/"))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No selflink found for identifier " + identifier))
                .getHref();
    }

    public static String getSelfLinkBySystemId(FintLinks resource) {
        return getSelfLinkBy("systemid", resource);
    }
}
