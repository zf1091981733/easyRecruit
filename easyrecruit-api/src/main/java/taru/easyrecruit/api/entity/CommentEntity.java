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
@TableName("api_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer commentId;
	/**
	 * 评论编号
	 */
	private String commentUuid;
	/**
	 * 发布人id
	 */
	private String userId;
	/**
	 * 给谁回复
	 */
	private String userPid;
	/**
	 * 在哪回复（讨论贴、招聘信息）
	 */
	private String areaId;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 发布时间
	 */
	private Date createTime;
	/**
	 * 状态（正常、已删除）
	 */
	private String status;
	/**
	 * 已读未读
	 */
	private String isRead;
	/**
	 * 层级
	 */
	private String lever;
	/**
	 * 区域类型(招聘信息、讨论贴)
	 */
	private String areaType;

}
