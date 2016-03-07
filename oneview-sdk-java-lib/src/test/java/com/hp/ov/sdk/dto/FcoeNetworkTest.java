package com.hp.ov.sdk.dto;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;


public class FcoeNetworkTest {

    @Test
    public void shouldRespectEqualsContract() {
        EqualsVerifier.forClass(FcoeNetwork.class)
                .withRedefinedSuperclass()
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.NULL_FIELDS)
                .verify();
    }

}
