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
@TableName("api_student")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer studentId;
	/**
	 * 编号
	 */
	private String studentUuid;
	/**
	 * 学校图片url地址
	 */
	private String studentLogo;
	/**
	 * 就读大学
	 */
	private String college;
	/**
	 * 主修专业
	 */
	private String major;
	/**
	 * 学号
	 */
	private String number;
	/**
	 * 姓名
	 */
	private String studentName;
	/**
	 * 期望职位
	 */
	private String position;
	/**
	 * 
	 */
	private Date createTime;

}
