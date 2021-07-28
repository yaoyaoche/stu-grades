package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.StuGradesDao;
import io.renren.modules.generator.entity.StuGradesEntity;
import io.renren.modules.generator.service.StuGradesService;


@Service("stuGradesService")
public class StuGradesServiceImpl extends ServiceImpl<StuGradesDao, StuGradesEntity> implements StuGradesService {

    @Autowired
    StuGradesDao stuGradesDao;
//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<StuGradesEntity> page = this.page(
//                new Query<StuGradesEntity>().getPage(params),
//                new QueryWrapper<StuGradesEntity>()
//        );
//
//        return new PageUtils(page);
//    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        IPage<StuGradesEntity> page = this.page(
                new Query<StuGradesEntity>().getPage(params),
                new QueryWrapper<StuGradesEntity>().like(StringUtils.isNotBlank(key),"name", key)
        );
        return new PageUtils(page);
    }

    public void gradessum(StuGradesEntity entity){
        entity.setSum(entity.getChinese()+entity.getEnglish()+entity.getMaths());
//        return stuGradesDao.gradessum(entity);
    }

    public List rank(){
        List<StuGradesEntity> list = stuGradesDao.selectList(null);
        list.sort((y,x) -> Integer.compare(x.getSum(),y.getSum()));
        return list;
    }

}
