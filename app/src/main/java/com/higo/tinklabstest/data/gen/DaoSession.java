package com.higo.tinklabstest.data.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.higo.tinklabstest.entity.CityGuide;

import com.higo.tinklabstest.data.gen.CityGuideDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cityGuideDaoConfig;

    private final CityGuideDao cityGuideDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cityGuideDaoConfig = daoConfigMap.get(CityGuideDao.class).clone();
        cityGuideDaoConfig.initIdentityScope(type);

        cityGuideDao = new CityGuideDao(cityGuideDaoConfig, this);

        registerDao(CityGuide.class, cityGuideDao);
    }
    
    public void clear() {
        cityGuideDaoConfig.clearIdentityScope();
    }

    public CityGuideDao getCityGuideDao() {
        return cityGuideDao;
    }

}
