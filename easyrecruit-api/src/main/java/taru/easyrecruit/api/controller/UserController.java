package taru.easyrecruit.api.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.entity.UserEntity;
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

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    //@RequiresPermissions("api:user:info")
    public R info(@PathVariable("userId") Integer userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:user:save")
    public R save(@RequestBody UserEntity user){
        Date now = new Date(System.currentTimeMillis());
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

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:user:delete")
    public R delete(@RequestBody Integer[] userIds){
		userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
