package com.screwfix.claim.statistics.views;

import com.screwfix.claim.statistics.models.Claim;
import io.dropwizard.views.View;

import java.util.List;

public class ClaimsView extends View {
    private final List<Claim> claims;

    public ClaimsView(List<Claim> claims) {
        super("claims.ftl");
        this.claims = claims;
    }

    public List<Claim> getClaim() {
        return claims;
    }
}
