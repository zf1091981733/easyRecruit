package taru.easyrecruit.api.entity;

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
@TableName("api_delivery")
public class DeliveryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer deliveryId;
	/**
	 * 投递审核编号
	 */
	private String deliveryUuid;
	/**
	 * 职位id
	 */
	private Integer positionId;
	/**
	 * 用户id(学生)
	 */
	private Integer userId;
	/**
	 * 简历id
	 */
	private Integer resumeId;
	/**
	 * 状态（简历筛选中、初选、笔试、面试、待发offer、结束）
	 */
	private String status;
	/**
	 * 
	 */
	private Date createTime;

}
