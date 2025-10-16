package no.fintlabs.adapter.models.sync;

import lombok.Data;
import no.fint.model.resource.FintLinks;
import no.fint.model.resource.FintResource;
import no.fintlabs.adapter.models.utils.LinkUtilities;

/**
 * Represents an entry in the page data.
 */
@Data
public class SyncPageEntry {
    /**
     * The unique identifier for the resource.
     * <p>
     * This is now stored as a <strong>raw identifier value</strong> (e.g. <code>32a83959-2896-4882-94b2-9157cad009d2</code>),
     * rather than a full self link URL.
     * </p>
     * <p>
     * Typically obtained via {@link LinkUtilities#getSelfLinkBy(String, FintLinks)}.
     * </p>
     */
    private String identifier;
    /**
     * The FINT resource.
     */
    private Object resource;

    /**
     * Creates a new {@link SyncPageEntry} using a provided identifier and resource.
     *
     * @param identifier The identifier value.
     * @param resource   The FINT resource object.
     * @return A new {@link SyncPageEntry} instance.
     */
    public static SyncPageEntry of(String identifier, Object resource) {
        SyncPageEntry entry = new SyncPageEntry();
        entry.setIdentifier(identifier);
        entry.setResource(resource);

        return entry;
    }

    /**
     * Helper method to create an SyncPageEntry with <code>systemId</code> as identifier.
     * @param resource {@link #resource}
     * @return A SyncPageEntry
     * @param <T> The FINT resource type
     */
    public static <T extends FintLinks> SyncPageEntry ofSystemId(T resource) {
        SyncPageEntry entry = new SyncPageEntry();

        entry.setIdentifier(LinkUtilities.getSelfLinkBySystemId(resource));
        entry.setResource(resource);

        return entry;
    }

    /**
     * Creates a {@link SyncPageEntry} using a specific identifier name.
     * <p>
     * The identifier is extracted from the self link matching the given identifier name
     * and returned as the raw value (last segment of the link).
     * </p>
     *
     * @param identifierName The name of the self link identifier (e.g. "feidenavn", "brukernavn", "elevnummer").
     * @param resource       The FINT resource.
     * @param <T>            The resource type extending {@link FintLinks}.
     * @return A {@link SyncPageEntry} instance with the identifier value extracted from the resource.
     * @see <a href="https://informasjonsmodell.felleskomponent.no/docs/elev_elev">FINT resource Elev</a>
     */
    public static <T extends FintLinks> SyncPageEntry ofIdentifierName(String identifierName, T resource) {
        SyncPageEntry entry = new SyncPageEntry();

        entry.setIdentifier(LinkUtilities.getSelfLinkBy(identifierName, resource));
        entry.setResource(resource);

        return entry;
    }
}
