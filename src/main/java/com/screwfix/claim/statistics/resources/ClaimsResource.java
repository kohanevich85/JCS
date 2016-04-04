package com.screwfix.claim.statistics.resources;

import com.screwfix.claim.statistics.dao.ClaimDAO;
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

import static java.util.Arrays.asList;

@Path("/api/claims")
@Produces(MediaType.APPLICATION_JSON)
public class ClaimsResource {
    private static final Logger LOGGER = Logger.getLogger(ClaimsResource.class);
    private final ClaimDAO claimDao;

    @Inject
    public ClaimsResource(ClaimDAO claimDao) {
        this.claimDao = claimDao;
    }

    // TODO: service
    @POST
    public List<Claim> getClaims(@BeanParam FilterParams paramBean) {
        System.out.println(paramBean);
        return asList(claimDao.findClaimById(paramBean));
    }
}
