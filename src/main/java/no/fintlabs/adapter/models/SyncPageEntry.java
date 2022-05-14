package no.fintlabs.adapter.models;

import lombok.Data;
import no.fint.model.resource.FintLinks;
import no.fintlabs.adapter.models.utils.LinkUtilities;

@Data
public class SyncPageEntry<T> {
    private String identifier;
    private T resource;

    public static <T> SyncPageEntry<T> of(String identifier, T resource) {
        SyncPageEntry<T> entry = new SyncPageEntry<>();
        entry.setIdentifier(identifier);
        entry.setResource(resource);

        return entry;
    }

    public static <R extends FintLinks> SyncPageEntry<R> ofSystemId(R resource) {
        SyncPageEntry<R> entry = new SyncPageEntry<>();

        entry.setIdentifier(LinkUtilities.getSelfLinkBySystemId(resource));
        entry.setResource(resource);

        return entry;
    }

    public static <R extends FintLinks> SyncPageEntry<R> ofIdentifierName(String identifierName, R resource) {
        SyncPageEntry<R> entry = new SyncPageEntry<>();

        entry.setIdentifier(LinkUtilities.getSelfLinkBy(identifierName, resource));
        entry.setResource(resource);

        return entry;
    }
}
