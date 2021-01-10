package taru.easyrecruit.api.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import taru.easyrecruit.api.dao.entity.RoleEntity;

@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
}
