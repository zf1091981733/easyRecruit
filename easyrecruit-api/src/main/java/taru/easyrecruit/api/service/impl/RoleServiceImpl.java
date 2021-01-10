package taru.easyrecruit.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import taru.easyrecruit.api.dao.RoleDao;
import taru.easyrecruit.api.dao.entity.RoleEntity;
import taru.easyrecruit.api.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao,RoleEntity> implements RoleService{
}
