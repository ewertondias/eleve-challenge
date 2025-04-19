package org.eleve.challenge.domain.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@RegisterForReflection
@AllArgsConstructor
@Getter
public class ErrorResponse {

    @Schema(description = "Error code")
    private int code;

    @Schema(description = "Error description")
    private String message;

}
