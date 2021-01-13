package taru.easyrecruit.api.controller;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.SessionUtil;
import taru.easyrecruit.api.common.utils.UuIdUtils;
import taru.easyrecruit.api.controller.mongo.doc.FileDoc;
import taru.easyrecruit.api.controller.mongo.FileMongo;
import taru.easyrecruit.api.dao.entity.ResumeEntity;
import taru.easyrecruit.api.service.ResumeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@Slf4j
@RestController
@RequestMapping("api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 查询学生简历列表
     */
    @RequestMapping("/list")
   // @RequiresPermissions("api:resume:list")
    public R list(@RequestParam Map<String, Object> params,HttpServletRequest request){
        String userId = String.valueOf(SessionUtil.getUserId(request));
        List<ResumeEntity> resumes = resumeService.list(new QueryWrapper<ResumeEntity>().eq("userId", userId));
        return R.ok("简历查询成功！").put("data", resumes);
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
    public R save(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        FileDoc saveFile;//保存成功后返回的对象
        try {
            saveFile = FileMongo.saveFile(file, request);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件保存失败，原因：{}",e.getMessage());
            return R.error("文件保存失败，原因："+e.getMessage());
        }
        //保存文件记录
        //截掉后缀
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        ResumeEntity resume = new ResumeEntity();
        resume.setResumeUuid(UuIdUtils.getUUID());
        resume.setResumeName(name);
        resume.setCreateTime(new Date(System.currentTimeMillis()));
        resume.setUserId(String.valueOf(SessionUtil.getUserId(request)));
        resume.setFileId(saveFile.getId());
        resume.setFileName(saveFile.getName());
        resumeService.save(resume);
        return R.ok("简历保存成功！");
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
    /**
     * 下载简历
     */
    @RequestMapping("download/{resumeId}")
    public R download(@PathVariable("resumeId") Integer resumeId, HttpServletRequest request,HttpServletResponse response){
        ResumeEntity resume = resumeService.getById(resumeId);
        //从mongo得到简历文档
        try {
            FileDoc file = mongoTemplate.findById(resume.getFileId(), FileDoc.class);
            FileMongo.downLoad(file,request,response);
        } catch (NullPointerException e) {
            log.error("下载文件失败：{}",e.getMessage());
            return R.error("下载文件失败，没有该资源");
        }catch (Exception e) {
            e.printStackTrace();
            log.error("下载文件失败：{}",e.getMessage());
            return R.error("下载文件失败，原因："+e.getMessage());
        }
        return R.ok("下载成功");
    }
}
