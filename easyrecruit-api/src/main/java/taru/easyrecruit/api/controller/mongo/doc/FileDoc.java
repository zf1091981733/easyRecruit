package taru.easyrecruit.api.controller.mongo.doc;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class FileDoc {
    @Id
    private String id;

    private String name;
    private Date createTime;
    private Binary content;
    private String contentType;
    private long size;

}
