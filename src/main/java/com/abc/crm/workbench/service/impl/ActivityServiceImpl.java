package com.abc.crm.workbench.service.impl;

import com.abc.crm.settings.dao.UserDao;
import com.abc.crm.settings.domain.User;
import com.abc.crm.vo.PageVO;
import com.abc.crm.workbench.dao.ActivityDao;
import com.abc.crm.workbench.dao.ActivityRemarkDao;
import com.abc.crm.workbench.domain.Activity;
import com.abc.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private UserDao userDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private ActivityRemarkDao activityRemarkDao;

    //添加
    @Override
    public boolean sava(Activity activity) {

        boolean flag = true;

        if (activityDao.sava(activity) != 1) {
            flag = false;
        }
        return flag;
    }

    //分页查询
    @Override
    public PageVO pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotal(map);

        //取得dataList
        List<Activity> list = activityDao.getActivityList(map);

        //创建一个vo对象,将total和dataList封装到vo中
        PageVO vo = new PageVO();
        vo.setTotal(total);
        vo.setDataList(list);

        //将vo返回
        return vo;
    }

    @Override
    public boolean delete(String[] id) {

        boolean flag = true;

        //先查询出需要删除的备注数量
        int count = activityRemarkDao.getCount(id);

        //删除备注 返回受到影响的条数(实际删除的条数)
        int number = activityRemarkDao.deleteCount(id);

        if (count != number) {

            flag = false;
        }
        //删除市场活动
        int num = activityDao.deleteCount(id);

        if (num != id.length) {

            flag = false;
        }

        return flag;
    }

    @Override
    public Map<String, Object> selectById(String id) {

        //uList
        List<User> list = userDao.getUserAll();

        //取单条市场活动
        Activity activity = activityDao.selectByIds(id);

        //放入map中
        Map<String, Object> map = new HashMap<>();

        map.put("uList", list);
        map.put("a", activity);

        return map;
    }

    @Override
    public Map<String, Object> updates(Activity activity) {

        boolean flag = false;

        if (activityDao.updates(activity) == 1) {
            flag = true;
        }

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);

        return map;
    }

    @Override
    public Activity selectDatail(String id) {

      Activity  activity=  activityDao.selectDatail(id);

        return activity;
    }
}
