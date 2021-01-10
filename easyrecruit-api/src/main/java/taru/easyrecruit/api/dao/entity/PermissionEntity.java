package taru.easyrecruit.api.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("api_permission")
public class PermissionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer permissionId;

    private String permissionName;

    private String permissionUrl;

}
