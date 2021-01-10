package taru.easyrecruit.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taru.easyrecruit.api.common.utils.R;
import taru.easyrecruit.api.common.utils.UuIdUtils;
import taru.easyrecruit.api.dao.entity.RoleEntity;
import taru.easyrecruit.api.service.RoleService;

@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/save")
    public R saveRole(@RequestBody RoleEntity role){
        role.setRoleUuid(UuIdUtils.getUUID());
        roleService.save(role);
        return R.ok();
    }
}
