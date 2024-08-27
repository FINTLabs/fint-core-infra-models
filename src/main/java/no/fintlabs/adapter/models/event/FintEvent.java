package no.fintlabs.adapter.models.event;

import no.fintlabs.adapter.operation.OperationType;

public interface FintEvent {

    public String getCorrId();
    public String getOrgId();
    public OperationType getOperationType();

}
