package taru.easyrecruit.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zhoufeng
 * @email zhoufeng08@foxmail.com
 * @date 2021-01-03 20:41:19
 */
@Data
@TableName("api_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 编号
	 */
	private String userUuid;
	/**
	 * 头像图片url地址
	 */
	private String userLogo;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 状态（正常、封禁）
	 */
	private String status;
	/**
	 * 角色（无、学生、企业）
	 */
	private String userRole;
	/**
	 * 学生认证（未认证、审核中、通过、不通过）
	 */
	private String userStudentAuthentication;
	/**
	 * 学生id
	 */
	private String userStudentId;
	/**
	 * 企业认证
	 */
	private String userCompanyAuthentication;
	/**
	 * 企业id
	 */
	private String userCompanyId;
	/**
	 * 盐值
	 */
	private String salt;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 密码
	 */
	private String password;
}
