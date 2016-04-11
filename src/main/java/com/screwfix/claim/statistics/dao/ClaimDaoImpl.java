package com.screwfix.claim.statistics.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Denys_Kohanevych on 4/1/2016
 */
public class ClaimDaoImpl implements ClaimDAO {

    private static final Logger LOGGER = Logger.getLogger(ClaimDaoImpl.class);
    private SqlSessionFactory sessionFactory;

    @Override
    public List<Claim> findClaims(FilterParams params) {
        try (SqlSession session = sessionFactory.openSession()) {
            ClaimDAO mapper = session.getMapper(ClaimDAO.class);
            return mapper.findClaims(params);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Inject
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
