package com.techie.microservices.order.stubs;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class InventoryClientStub {
    public static void stubInventory(String skuCode, Integer quantity) {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/v1/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
