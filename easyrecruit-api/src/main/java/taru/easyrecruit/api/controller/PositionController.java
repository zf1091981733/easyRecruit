package taru.easyrecruit.api.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.entity.PositionEntity;
import taru.easyrecruit.api.service.PositionService;



/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:position:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = positionService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 根据用户id查询列表
     */
    @RequestMapping("/list/{userId}")
    public R listByUserId(@PathVariable("userId") Integer userId){
        List<PositionEntity> data = positionService.list(new QueryWrapper<PositionEntity>().eq("user_id", userId));
        return R.ok().put("data",data);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{positionId}")
    //@RequiresPermissions("api:position:info")
    public R info(@PathVariable("positionId") Integer positionId){
		PositionEntity position = positionService.getById(positionId);

        return R.ok().put("position", position);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:position:save")
    public R save(@RequestBody PositionEntity position){
        position.setCreateTime(new Date(System.currentTimeMillis()));
		positionService.save(position);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:position:update")
    public R update(@RequestBody PositionEntity position){
		positionService.updateById(position);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:position:delete")
    public R delete(@RequestBody Integer[] positionIds){
		positionService.removeByIds(Arrays.asList(positionIds));

        return R.ok();
    }

}
