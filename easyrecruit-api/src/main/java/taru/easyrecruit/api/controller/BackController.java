package taru.easyrecruit.api.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.entity.BackEntity;
import taru.easyrecruit.api.service.BackService;



/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/back")
public class BackController {
    @Autowired
    private BackService backService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:back:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = backService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{backId}")
    //@RequiresPermissions("api:back:info")
    public R info(@PathVariable("backId") Integer backId){
		BackEntity back = backService.getById(backId);

        return R.ok().put("back", back);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:back:save")
    public R save(@RequestBody BackEntity back){
		backService.save(back);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:back:update")
    public R update(@RequestBody BackEntity back){
		backService.updateById(back);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:back:delete")
    public R delete(@RequestBody Integer[] backIds){
		backService.removeByIds(Arrays.asList(backIds));

        return R.ok();
    }

}
