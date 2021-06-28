package com.abc.crm.workbench.dao;

import com.abc.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityDao {

    int sava(Activity activity);

    List<Activity> getActivityList(Map<String, Object> map);

    int getTotal(Map<String, Object> map);

    int deleteCount(String[] id);

    Activity selectByIds(String id);

    int updates(Activity activity);

    Activity selectDatail(String id);
}
