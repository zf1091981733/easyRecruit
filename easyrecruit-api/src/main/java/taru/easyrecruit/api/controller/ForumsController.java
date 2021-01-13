package taru.easyrecruit.api.controller;

import com.mongodb.client.result.UpdateResult;
import com.sun.javafx.binding.IntegerConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import taru.easyrecruit.api.common.exception.RRException;
import taru.easyrecruit.api.common.utils.Constant;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.SessionUtil;
import taru.easyrecruit.api.controller.mongo.doc.ForumsDoc;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据源mongodb
 * 文档ForumsDoc
 */
@Slf4j
@RestController
@RequestMapping("api/forums")
public class ForumsController {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("api:delivery:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(Criteria.where("state").is(1));
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of((Integer) params.get(Constant.PAGE), (Integer) params.get(Constant.LIMIT), sort);
        query.with(pageable);
        List<ForumsDoc> forumsDocs = mongoTemplate.find(query, ForumsDoc.class);
        return R.ok("查询成功").put("data", forumsDocs);
    }
    /**
     * 查询用户已发布
     */
    @RequestMapping("/find")
    public R find(HttpServletRequest request){
        Integer userId = SessionUtil.getUserId(request);
        Query query = new Query(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("state").is(1));
        List<ForumsDoc> forumsDocs = mongoTemplate.find(query, ForumsDoc.class);
        return R.ok("查询成功").put("data",forumsDocs);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{forumsId}")
    //@RequiresPermissions("api:delivery:info")
    public R info(@PathVariable("forumsId") String forumsId) {
        ForumsDoc forums = mongoTemplate.findById(forumsId, ForumsDoc.class);
        return R.ok().put("data", forums);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("api:delivery:save")
    public R save(@RequestBody ForumsDoc forumsDoc, HttpServletRequest request) {
        if (StringUtils.isBlank(forumsDoc.getTitle()) || StringUtils.isBlank(forumsDoc.getContent())) {
            return R.error("文档不能为空");
        }
        forumsDoc.setUserId(SessionUtil.getUserId(request));
        forumsDoc.setCreateTime(new Date(System.currentTimeMillis()));
        forumsDoc.setState(1);
        mongoTemplate.save(forumsDoc);
        return R.ok("保存成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("api:delivery:update")
    public R update(@RequestBody ForumsDoc forumsDoc) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(forumsDoc.getId()));
            Update update = new Update();
            update.set("title", forumsDoc.getTitle());
            update.set("type", forumsDoc.getType());
            update.set("content", forumsDoc.getContent());
            UpdateResult result = mongoTemplate.upsert(query, update, ForumsDoc.class);
            long modifiedCount = result.getModifiedCount();
            if (modifiedCount <= 0) {
                throw new RRException("更新失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("更新成功");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("api:delivery:delete")
    public R delete(@RequestBody String[] forumsIds) {
        try {
            for (String id : forumsIds) {
                Query query = new Query();
                query.addCriteria(Criteria.where("_id").is(id));
                Update update = new Update();
                update.set("state", 0);
                UpdateResult result = mongoTemplate.upsert(query, update, ForumsDoc.class);
                long modifiedCount = result.getModifiedCount();
                if (modifiedCount <= 0) {
                    throw new RRException("删除失败");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok("删除成功");
    }

}
