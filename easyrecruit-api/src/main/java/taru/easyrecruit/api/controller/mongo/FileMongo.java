package taru.easyrecruit.api.controller.mongo;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import taru.easyrecruit.api.controller.mongo.doc.FileDoc;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * 数据源mongodb
 */
@Slf4j
@Component
public class FileMongo {

    @Autowired
    private MongoTemplate autoMongoTemplate;
    private static MongoTemplate mongoTemplate;

    @PostConstruct
    public void init(){
        mongoTemplate = this.autoMongoTemplate;
    }

    /**
     * 下载文件
     */
    public static void downLoad(FileDoc file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] data = null;
        String fileName =file.getName();
        if (file!=null){
            data = file.getContent().getData();
        }
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        try {
            OutputStream os = response.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException("下载文件IO流错误");
        }
    }
    /**
     * 保存文件
     */
    public static FileDoc saveFile(MultipartFile file,HttpServletRequest request) throws IOException {
        FileDoc fileDoc = new FileDoc();
        String fileName = file.getOriginalFilename();
        fileDoc.setName(fileName);
        fileDoc.setCreateTime(new Date(System.currentTimeMillis()));
        fileDoc.setContent(new Binary(file.getBytes()));
        fileDoc.setContentType(file.getContentType());
        fileDoc.setSize(file.getSize());
        //文件保存至mongo
        return mongoTemplate.save(fileDoc);
    }
}
