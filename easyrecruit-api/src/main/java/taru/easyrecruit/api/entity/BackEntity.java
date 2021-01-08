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
@TableName("api_back")
public class BackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer backId;
	/**
	 * 反馈编号
	 */
	private String backUuid;
	/**
	 * 反馈类型（举报、意见）
	 */
	private String backType;
	/**
	 * 内存
	 */
	private String backContent;
	/**
	 * 提交时间
	 */
	private Date createTime;
	/**
	 * 状态（未处理、处理中、已处理）
	 */
	private String status;
	/**
	 * 用户id
	 */
	private Integer userId;

}
