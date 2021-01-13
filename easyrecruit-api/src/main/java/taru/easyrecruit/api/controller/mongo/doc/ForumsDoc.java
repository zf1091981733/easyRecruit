package taru.easyrecruit.api.controller.mongo.doc;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 讨论贴文档
 */
@Data
@Document
public class ForumsDoc {
    @Id
    private String id;

    private Integer userId;
    private String title;
    private String type;
    private Date createTime;
    private String content;
    /**
     * 是否存在
     */
    private Integer state;

}
