package taru.easyrecruit.api.service.impl;

import taru.easyrecruit.api.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.dao.PositionDao;
import taru.easyrecruit.api.dao.entity.PositionEntity;
import taru.easyrecruit.api.service.PositionService;


@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionDao, PositionEntity> implements PositionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PositionEntity> page = this.page(
                new Query<PositionEntity>().getPage(params),
                new QueryWrapper<PositionEntity>()
                .like("title",params.get("query"))
                .like("position_name",params.get("query"))
                .like("position_type",params.get("position_type"))
                .like("address",params.get("address"))
        );

        return new PageUtils(page);
    }

}