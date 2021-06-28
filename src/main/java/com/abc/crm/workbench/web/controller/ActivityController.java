package com.abc.crm.workbench.web.controller;

import com.abc.crm.settings.domain.User;
import com.abc.crm.settings.service.UserService;
import com.abc.crm.utils.DateTimeUtil;
import com.abc.crm.utils.UUIDUtil;
import com.abc.crm.vo.PageVO;
import com.abc.crm.workbench.domain.Activity;
import com.abc.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("activity")
public class ActivityController {


    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @RequestMapping("/get.do")
    @ResponseBody
    public List<User> showUserAll() {

        System.out.println("取得用户列表");

        return userService.getUserAll();
    }

    @RequestMapping(value = "/save.do")
    @ResponseBody()
    public Map<String, Object> add(Activity activity, HttpServletRequest request) {

        System.out.println("执行市场活动的添加操作");

        String id = UUIDUtil.getUUID();
        //创建系统当前时间
        String createTime = DateTimeUtil.getSysTime();
        //从session中获取用户信息
        String createBy = ((User) request.getSession().getAttribute("user")).getName();

        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        boolean flag = activityService.sava(activity);

        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);

        return map;
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    public PageVO pageList(Activity activity, String pageNo, String pageSize) {

        System.out.println("进入到查询市场活动信息列表【条件查询+分页查询】");

        //每页展示的记录数
        int pageNo2 = Integer.valueOf(pageNo);
        int pageSize2 = Integer.valueOf(pageSize);
        //计算出略过的记录数
        int skipCount = (pageNo2 - 1) * pageSize2;

        Map<String, Object> map = new HashMap<>();
        map.put("a", activity);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize2);

        //业务层拿到数据后 使用vo的形式返回
        PageVO pageVO = activityService.pageList(map);
        return pageVO;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String, Boolean> delete(String[] id) {
        System.out.println("执行市场活动的删除活动");

        boolean flag = activityService.delete(id);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", flag);

        return map;
    }


    @RequestMapping("/updateInfo.do")
    @ResponseBody
    public Map<String, Object> updateInfo(String id) {

        System.out.println("进入查询用户信息列表和根据市场活动id查单条记录");

        String ids = id;

        Map<String, Object> map = activityService.selectById(ids);

        return map;
    }

    @RequestMapping("/updates.do")
    @ResponseBody
    public Map<String, Object> update(Activity activity) {

        System.out.println("进入到修改市场活动");
        System.out.println(activity);

        Map<String, Object> map = activityService.updates(activity);
        System.out.println(map);

        return map;
    }

    @RequestMapping("/detail.do")
    public void datail(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到跳转到详细信息页");

      Activity activity =activityService.selectDatail(id);

      //使用传统请求跳转到某条活动的详细信息页
        request.setAttribute("a",activity);

        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);

    }



}
