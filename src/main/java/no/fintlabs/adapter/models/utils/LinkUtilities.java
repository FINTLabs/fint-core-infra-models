package no.fintlabs.adapter.models.utils;

import no.fint.model.resource.FintLinks;
import no.fintlabs.adapter.models.sync.SyncPageEntry;

/**
 * Helper methods to obtain self links from a {@link no.fint.model.resource.FintLinks FintLinks } resource.
 */
public class LinkUtilities {

    /**
     * Gets a self link from a {@link no.fint.model.resource.FintLinks FintLinks } resource. For more detalis
     * see {@link SyncPageEntry#ofIdentifierName(String, FintLinks) SyncPageEntry.ofIdentifierName()}
     * @param identifier The identifier.
     * @param resource {@link no.fint.model.resource.FintLinks FintLinks } resource.
     * @return The self link.
     */
    public static String getSelfLinkBy(String identifier, FintLinks resource) {
        return resource
                .getSelfLinks()
                .stream()
                .filter(link -> link.getHref().contains("/" + identifier + "/"))
                .findAny()
                .map(link -> {
                    String href = link.getHref();
                    return href.substring(href.lastIndexOf('/') + 1);
                })
                .orElseThrow(() -> new IllegalArgumentException("No selflink found for identifier " + identifier));
    }

    /**
     *
     * @param resource {@link no.fint.model.resource.FintLinks FintLinks } resource
     * @return The self link.
     */
    public static String getSelfLinkBySystemId(FintLinks resource) {
        return getSelfLinkBy("systemid", resource);
    }
}
