package taru.easyrecruit.api.service.impl;

import org.springframework.stereotype.Service;
import taru.easyrecruit.api.common.utils.Query;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.dao.DeliveryDao;
import taru.easyrecruit.api.entity.DeliveryEntity;
import taru.easyrecruit.api.service.DeliveryService;


@Service("deliveryService")
public class DeliveryServiceImpl extends ServiceImpl<DeliveryDao, DeliveryEntity> implements DeliveryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DeliveryEntity> page = this.page(
                new Query<DeliveryEntity>().getPage(params),
                new QueryWrapper<DeliveryEntity>()
        );

        return new PageUtils(page);
    }

}