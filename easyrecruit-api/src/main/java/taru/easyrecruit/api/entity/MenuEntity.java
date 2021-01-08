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
@TableName("api_menu")
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer menuId;
	/**
	 * 
	 */
	private String menuUuid;
	/**
	 * 
	 */
	private String menuName;
	/**
	 * 
	 */
	private String menuPid;
	/**
	 * 菜单类型（学生、企业、职位类型、讨论贴）
	 */
	private String type;

}
