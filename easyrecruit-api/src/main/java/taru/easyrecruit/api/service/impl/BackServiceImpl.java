package taru.easyrecruit.api.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.common.utils.Query;
import taru.easyrecruit.api.dao.BackDao;
import taru.easyrecruit.api.dao.entity.BackEntity;
import taru.easyrecruit.api.service.BackService;


@Service("backService")
public class BackServiceImpl extends ServiceImpl<BackDao, BackEntity> implements BackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BackEntity> page = this.page(
                new Query<BackEntity>().getPage(params),
                new QueryWrapper<BackEntity>()
        );

        return new PageUtils(page);
    }

}