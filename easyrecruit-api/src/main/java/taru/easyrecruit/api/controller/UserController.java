package taru.easyrecruit.api.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.UuIdUtils;
import taru.easyrecruit.api.dao.entity.UserEntity;
import taru.easyrecruit.api.service.UserService;



/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 列表
     */
//    @RequestMapping("/list")
//   // @RequiresPermissions("api:user:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = userService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("api:user:info")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);
        logger.info("查询用户:{}信息-------------",userId);
        return R.ok().put("user", user);
    }

    /**
     * 注册用户
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:user:save")
    public R save(@RequestBody UserEntity user){
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUserUuid(UuIdUtils.getUUID());
		userService.save(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//   // @RequiresPermissions("api:user:delete")
//    public R delete(@RequestBody Integer[] userIds){
//		userService.removeByIds(Arrays.asList(userIds));
//
//        return R.ok();
//    }

}
