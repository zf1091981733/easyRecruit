package taru.easyrecruit.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import taru.easyrecruit.api.dao.PermissionDao;
import taru.easyrecruit.api.dao.entity.PermissionEntity;
import taru.easyrecruit.api.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionEntity> implements PermissionService {
}
