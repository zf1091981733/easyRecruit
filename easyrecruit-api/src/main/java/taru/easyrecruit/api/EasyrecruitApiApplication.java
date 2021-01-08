package taru.easyrecruit.api;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("taru/easyrecruit/api/dao")
@SpringBootApplication
public class EasyrecruitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyrecruitApiApplication.class, args);
    }

}
