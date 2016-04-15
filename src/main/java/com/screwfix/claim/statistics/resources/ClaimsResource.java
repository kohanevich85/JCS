package com.screwfix.claim.statistics.resources;

import com.screwfix.claim.statistics.services.dao.ClaimDao;
import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/claims")
@Produces(MediaType.APPLICATION_JSON)
public class ClaimsResource {
    private static final Logger LOGGER = Logger.getLogger(ClaimsResource.class); // TODO:
    private final ClaimDao claimDao;

    @Inject
    public ClaimsResource(ClaimDao claimDao) {
        this.claimDao = claimDao;
    }

    @POST
    public List<Claim> getClaims(@BeanParam FilterParams paramBean) {
        return claimDao.findClaims(paramBean);
    }
}
