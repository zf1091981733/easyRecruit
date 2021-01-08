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
import taru.easyrecruit.api.entity.ResumeEntity;
import taru.easyrecruit.api.service.ResumeService;



/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:resume:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = resumeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{resumeId}")
    //@RequiresPermissions("api:resume:info")
    public R info(@PathVariable("resumeId") Integer resumeId){
		ResumeEntity resume = resumeService.getById(resumeId);

        return R.ok().put("resume", resume);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:resume:save")
    public R save(@RequestBody ResumeEntity resume){
		resumeService.save(resume);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:resume:update")
    public R update(@RequestBody ResumeEntity resume){
		resumeService.updateById(resume);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:resume:delete")
    public R delete(@RequestBody Integer[] resumeIds){
		resumeService.removeByIds(Arrays.asList(resumeIds));

        return R.ok();
    }

}
