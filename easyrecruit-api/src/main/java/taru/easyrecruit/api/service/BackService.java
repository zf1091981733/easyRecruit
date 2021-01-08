package taru.easyrecruit.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.entity.BackEntity;

import java.util.Map;

/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
public interface BackService extends IService<BackEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

