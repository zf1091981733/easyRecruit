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
@TableName("api_resume")
public class ResumeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer resumeId;
	/**
	 * 编号
	 */
	private String resumeUuid;
	/**
	 * 简历名
	 */
	private String resumeName;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态（正常、已删除）
	 */
	private String status;
	/**
	 * 用户id(学生)
	 */
	private String userId;

}
