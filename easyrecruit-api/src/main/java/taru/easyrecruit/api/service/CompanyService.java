package taru.easyrecruit.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.entity.CompanyEntity;

import java.util.Map;

/**
 * 
 *
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
public interface CompanyService extends IService<CompanyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

