package taru.easyrecruit.api.service.impl;

import taru.easyrecruit.api.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.dao.ResumeDao;
import taru.easyrecruit.api.entity.ResumeEntity;
import taru.easyrecruit.api.service.ResumeService;


@Service("resumeService")
public class ResumeServiceImpl extends ServiceImpl<ResumeDao, ResumeEntity> implements ResumeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ResumeEntity> page = this.page(
                new Query<ResumeEntity>().getPage(params),
                new QueryWrapper<ResumeEntity>()
        );

        return new PageUtils(page);
    }

}