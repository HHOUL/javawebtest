package com.abc.crm.workbench.service;

import com.abc.crm.vo.PageVO;
import com.abc.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {

    boolean sava(Activity activity);

    PageVO pageList(Map<String, Object> map);

    boolean delete(String[] id);

    Map<String,Object> selectById(String id);

    Map<String, Object> updates(Activity activity);

    Activity selectDatail(String id);
}
