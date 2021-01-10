package taru.easyrecruit.api.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("api_role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer roleId;

    private String roleUuid;

    private String roleName;

    private String info;

    private List<PermissionEntity> permissions;

}
