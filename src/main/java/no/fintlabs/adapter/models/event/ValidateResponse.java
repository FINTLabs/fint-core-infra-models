package no.fintlabs.adapter.models.event;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
@Builder
public class ValidateResponse implements Serializable {

    private static final long serialVersionUID = -1075199100920918308L;
    /**
     * This message should be set if something goes wrong. It should typically describe what went wrong,
     * for example a stack trace or an error message.
     */
    private String message;

    /**
     * This status code should be set to some code that can be used to trace the origin of the error
     * in the back end system.
     */
    private String statusCode;

    /**
     * Shows the status of the event that was processed by the adapter.
     */
    private ResponseStatus responseStatus;

    /**
     * Shows the problems the system has found when processing this event's request.
     */
    private List<Problem> problems;

    public static ValidateResponse ofResponseEvent(ResponseFintEvent responseEvent) {
        return ValidateResponse.builder()
                .message(getMessage(responseEvent))
                .statusCode(getStatusCode(responseEvent))
                .responseStatus(ResponseStatus.byResponseEvent(responseEvent))
                .problems(new ArrayList<>())
                .build();
    }

    private static String getStatusCode(ResponseFintEvent responseEvent) {
        if (responseEvent.isFailed()) {
            return "ERROR";
        } else if (responseEvent.isRejected()) {
            return "REJECTED";
        } else if (responseEvent.isConflicted()) {
            return "CONFLICT";
        }

        return "";
    }

    private static String getMessage(ResponseFintEvent responseEvent) {
        Function<String, String> setMessage = (message) -> ObjectUtils.isEmpty(message) ? message : "";

        if (responseEvent.isFailed()) {
            return setMessage.apply(responseEvent.getErrorMessage());
        } else if (responseEvent.isRejected()) {
            return setMessage.apply(responseEvent.getRejectReason());
        } else if (responseEvent.isConflicted()) {
            return setMessage.apply(responseEvent.getConflictReason());
        }

        return "";
    }

}