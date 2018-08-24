package com.worksap.stm2017.Service;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.SettingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {
    private SettingDao settingDao;

    @Autowired
    public SettingServiceImpl(DaoFactory daoFactory) {
        this.settingDao = daoFactory.getSettingDao();
    }

    public boolean getSetFree() {
        return settingDao.getSetFree();
    }

    public int getWorkload() {
        return settingDao.getWorkload();
    }

    public void setSetFree(boolean flag) {
        settingDao.setSetFree(flag);
    }

    public void setWorkload(int workload) {
        settingDao.setWorkload(workload);
    }
}
