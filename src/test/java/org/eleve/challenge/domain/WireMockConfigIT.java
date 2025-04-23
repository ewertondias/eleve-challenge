package org.eleve.challenge.domain;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static java.util.Objects.nonNull;

public class WireMockConfigIT {

    protected static WireMockServer wireMockServer;

    private static final String FOLDER = "src/test/resources/mock";
    private static final int PORT = 8085;

    @BeforeEach
    void start() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig()
            .withRootDirectory(FOLDER)
            .port(PORT)
            .notifier(new ConsoleNotifier(true)));

        wireMockServer.start();
    }

    @AfterEach
    void stop() {
        if (nonNull(wireMockServer)) {
            wireMockServer.stop();
        }
    }

    public static void exchangeRateApiSuccess(String rateBase, String rateTarget) {
        wireMockServer.stubFor(WireMock.get(WireMock.urlPathTemplate("/pair/{rateBase}/{rateTarget}"))
            .withPathParam("rateBase", WireMock.equalTo(rateBase))
            .withPathParam("rateTarget", WireMock.equalTo(rateTarget))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                .withBodyFile("ExchangeRate_API_Sucess.json")
            )
        );
    }

}
