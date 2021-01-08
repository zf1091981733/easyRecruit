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
@TableName("api_company")
public class CompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer companyId;
	/**
	 * 
	 */
	private String companyUuid;
	/**
	 * 企业logo图片url地址
	 */
	private String companyLogo;
	/**
	 * 企业名
	 */
	private String companyName;
	/**
	 * 员工名
	 */
	private String staffName;
	/**
	 * 工号
	 */
	private String number;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 简介
	 */
	private String companyResume;
	/**
	 * 
	 */
	private Date createTime;

}
