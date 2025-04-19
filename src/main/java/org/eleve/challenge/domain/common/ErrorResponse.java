package org.eleve.challenge.domain.common;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ErrorResponse {

    @Schema(description = "Error code")
    private String code;

    @Schema(description = "Error description")
    private String message;

}
