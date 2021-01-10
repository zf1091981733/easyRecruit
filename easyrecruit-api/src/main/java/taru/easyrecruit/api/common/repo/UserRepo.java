package taru.easyrecruit.api.common.repo;

import lombok.Data;

@Data
public class UserRepo {
    private Integer userId;
    private String userUuid;
    private String logo;
    private String nickName;
    private String userName;
    private String phone;
    private String email;
    private String roleId;
    private String userStudentAuthentication;
    private String studentId;
    private String userCompanyAuthentication;
    private String companyId;
    private String createTime;
}
