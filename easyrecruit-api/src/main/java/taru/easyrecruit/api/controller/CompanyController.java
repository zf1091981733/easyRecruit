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
import taru.easyrecruit.api.entity.CompanyEntity;
import taru.easyrecruit.api.service.CompanyService;


/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:company:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = companyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{companyId}")
    //@RequiresPermissions("api:company:info")
    public R info(@PathVariable("companyId") Integer companyId){
		CompanyEntity company = companyService.getById(companyId);

        return R.ok().put("company", company);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:company:save")
    public R save(@RequestBody CompanyEntity company){
		companyService.save(company);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:company:update")
    public R update(@RequestBody CompanyEntity company){
		companyService.updateById(company);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:company:delete")
    public R delete(@RequestBody Integer[] companyIds){
		companyService.removeByIds(Arrays.asList(companyIds));

        return R.ok();
    }

}
