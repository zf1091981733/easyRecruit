package taru.easyrecruit.api.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
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
@TableName("api_position")
public class PositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer positionId;
	/**
	 * 编号
	 */
	private String positionUuid;
	/**
	 * 职位招聘标题
	 */
	private String title;
	/**
	 * 职位名称
	 */
	private String positionName;
	/**
	 * 职位类别
	 */
	private String positionType;
	/**
	 * 地点
	 */
	private String address;
	/**
	 * 薪资
	 */
	private String salary;
	/**
	 * 职位描述
	 */
	private String positionInfo;
	/**
	 * 职位要求
	 */
	private String positionRequirement;
	/**
	 * 状态（正常、封禁、已删除）
	 */
	private String status;
	/**
	 * 审核（未审核、审核中、通过、不通过）
	 */
	private String authentication;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 用户id(企业)
	 */
	private String userId;

}
