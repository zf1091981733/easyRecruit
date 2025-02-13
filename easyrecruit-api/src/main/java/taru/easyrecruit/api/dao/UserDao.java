package taru.easyrecruit.api.dao;

import taru.easyrecruit.api.dao.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
