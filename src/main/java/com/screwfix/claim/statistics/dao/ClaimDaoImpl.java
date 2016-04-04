package com.screwfix.claim.statistics.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import javax.inject.Inject;

/**
 * Created by Denys_Kohanevych on 4/1/2016
 */
public class ClaimDaoImpl implements ClaimDAO {

    private static final Logger LOGGER = Logger.getLogger(ClaimDaoImpl.class);
    @Inject
    private SqlSessionFactory sessionFactory;

    @Override
    public Claim findClaimById(FilterParams params) {
        SqlSession session = sessionFactory.openSession();
        try {
            ClaimDAO mapper = session.getMapper(ClaimDAO.class);
            return mapper.findClaimById(params);
        }catch (Exception e){
            LOGGER.error(e);
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    //@Inject
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
