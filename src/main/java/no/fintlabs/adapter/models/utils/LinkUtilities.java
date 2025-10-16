package no.fintlabs.adapter.models.utils;

import no.fint.model.resource.FintLinks;
import no.fintlabs.adapter.models.sync.SyncPageEntry;

/**
 * Utility methods for working with {@link no.fint.model.resource.FintLinks FintLinks} resources.
 * <p>
 * These methods help extract the self link identifiers (e.g. systemId values)
 * from a FINT resource. The returned value is now only the final identifier segment,
 * not the full link URL.
 * </p>
 */
public class LinkUtilities {

    /**
     * Retrieves the identifier value from a {@link no.fint.model.resource.FintLinks FintLinks} resource
     * based on a specific identifier name.
     * <p>
     * For example, if a self link is:
     * <pre>
     * https://api.fintlabs.no/administrasjon/personalressurs/systemid/abc123
     * </pre>
     * this method will return:
     * <pre>
     * abc123
     * </pre>
     * </p>
     *
     * <p>For more details, see
     * {@link SyncPageEntry#ofIdentifierName(String, FintLinks) SyncPageEntry.ofIdentifierName()}.
     * </p>
     *
     * @param identifier The name of the identifier in the URL path (e.g. "systemid").
     * @param resource   The {@link no.fint.model.resource.FintLinks FintLinks} resource.
     * @return The raw identifier value (the last path segment of the self link).
     * @throws IllegalArgumentException if no self link is found for the given identifier.
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
     * Retrieves the identifier value from a {@link no.fint.model.resource.FintLinks FintLinks}
     * resource using the default identifier name <code>systemid</code>.
     * <p>
     * This is equivalent to calling:
     * <pre>
     * getSelfLinkBy("systemid", resource);
     * </pre>
     * </p>
     *
     * @param resource The {@link no.fint.model.resource.FintLinks FintLinks} resource.
     * @return The raw systemId value.
     */
    public static String getSelfLinkBySystemId(FintLinks resource) {
        return getSelfLinkBy("systemid", resource);
    }
}
