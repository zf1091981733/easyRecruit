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
import taru.easyrecruit.api.entity.CommentEntity;
import taru.easyrecruit.api.service.CommentService;


/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:comment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commentId}")
    //@RequiresPermissions("api:comment:info")
    public R info(@PathVariable("commentId") Integer commentId){
		CommentEntity comment = commentService.getById(commentId);

        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
   // @RequiresPermissions("api:comment:save")
    public R save(@RequestBody CommentEntity comment){
		commentService.save(comment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:comment:update")
    public R update(@RequestBody CommentEntity comment){
		commentService.updateById(comment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("api:comment:delete")
    public R delete(@RequestBody Integer[] commentIds){
		commentService.removeByIds(Arrays.asList(commentIds));

        return R.ok();
    }

}
