package no.fintlabs.adapter.models;

import lombok.Data;
import no.fint.model.resource.FintLinks;
import no.fintlabs.adapter.models.utils.LinkUtilities;

/**
 * Represents an entry in the page data.
 * @param <T> The FINT resource type for the page.
 */
@Data
public class SyncPageEntry<T> {
    /**
     * <p>
     * The uniq indentifier for the resource. Typically created with <code>{@link no.fint.model.resource.Link#with() Link.with()}</code>.
     * </p>
     * <p>
     * E.g. <code>${utdanning.vurdering.fravar}/systemid/32a83959-2896-4882-94b2-9157cad009d2</code>
     * </p>
     */
    private String identifier;
    /**
     * The FINT resource.
     */
    private T resource;

    /**
     * Helper method to create an SyncPageEntry.
     * @param identifier {@link #identifier}
     * @param resource {@link #resource}
     * @return A SyncPageEntry.
     * @param <T> The FINT resource type
     */
    public static <T> SyncPageEntry<T> of(String identifier, T resource) {
        SyncPageEntry<T> entry = new SyncPageEntry<>();
        entry.setIdentifier(identifier);
        entry.setResource(resource);

        return entry;
    }

    /**
     * Helper method to create an SyncPageEntry with <code>systemId</code> as identifier.
     * @param resource {@link #resource}
     * @return A SyncPageEntry
     * @param <R> The FINT resource type
     */
    public static <R extends FintLinks> SyncPageEntry<R> ofSystemId(R resource) {
        SyncPageEntry<R> entry = new SyncPageEntry<>();

        entry.setIdentifier(LinkUtilities.getSelfLinkBySystemId(resource));
        entry.setResource(resource);

        return entry;
    }

    /**
     * Helper method to create an SyncPageEntry with identifierName as the identifier.
     * @param identifierName Name of a self link in a FINT resource. This is any attribute
     *                       of the type {@link no.fint.model.felles.kompleksedatatyper.Identifikator Identifikator}.
     *                       For the FINT resource <code>Elev</code> it could be:
     *                       <ul>
     *                       <li><code>elevnummer</code></li>
     *                       <li><code>brukernavn</code></li>
     *                       <li><code>feidenavn</code></li>
     *                       </ul>
     * @param resource {@link #resource}
     * @return A SyncPageEntry
     * @param <R> The FINT resource type
     *
     * @see <a href="https://informasjonsmodell.felleskomponent.no/docs/elev_elev">FINT resource Elev</a>
     */
    public static <R extends FintLinks> SyncPageEntry<R> ofIdentifierName(String identifierName, R resource) {
        SyncPageEntry<R> entry = new SyncPageEntry<>();

        entry.setIdentifier(LinkUtilities.getSelfLinkBy(identifierName, resource));
        entry.setResource(resource);

        return entry;
    }
}
