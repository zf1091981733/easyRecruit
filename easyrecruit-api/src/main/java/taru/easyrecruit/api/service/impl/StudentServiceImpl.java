package taru.easyrecruit.api.service.impl;

import taru.easyrecruit.api.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import taru.easyrecruit.api.common.utils.PageUtils;
import taru.easyrecruit.api.dao.StudentDao;
import taru.easyrecruit.api.dao.entity.StudentEntity;
import taru.easyrecruit.api.service.StudentService;


@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StudentEntity> page = this.page(
                new Query<StudentEntity>().getPage(params),
                new QueryWrapper<StudentEntity>()
        );

        return new PageUtils(page);
    }

}