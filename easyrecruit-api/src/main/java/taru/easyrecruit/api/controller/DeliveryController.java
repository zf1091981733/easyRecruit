package taru.easyrecruit.api.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.dao.entity.DeliveryEntity;
import taru.easyrecruit.api.service.DeliveryService;



/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:delivery:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = deliveryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deliveryId}")
    //@RequiresPermissions("api:delivery:info")
    public R info(@PathVariable("deliveryId") Integer deliveryId){
		DeliveryEntity delivery = deliveryService.getById(deliveryId);

        return R.ok().put("delivery", delivery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:delivery:save")
    public R save(@RequestBody DeliveryEntity delivery){
		deliveryService.save(delivery);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:delivery:update")
    public R update(@RequestBody DeliveryEntity delivery){
		deliveryService.updateById(delivery);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:delivery:delete")
    public R delete(@RequestBody Integer[] deliveryIds){
		deliveryService.removeByIds(Arrays.asList(deliveryIds));

        return R.ok();
    }

}
