package taru.easyrecruit.easyrecruitapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;


public class MongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void mongoTest(){
        String str = "resume.pdf";
        System.out.println(str.substring(0,str.lastIndexOf(".")));
    }
}
