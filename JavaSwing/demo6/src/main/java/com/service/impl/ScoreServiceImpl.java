package com.service.impl;

import com.dao.ScoreDao;
import com.entity.ResultScore;
import com.entity.Score;
import com.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ScoreServiceImpl")
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    /**
     * 新增一条成绩记录
     * @param score
     * @return
     */
    @Override
    public boolean addScore(Score score){
        int flag = scoreDao.addScore(score);
        return flag!=0;
    }

    /**
     * 按一定条件查找成绩记录
     * @param score
     * @return
     */
    @Override
    public List<Score> getScoreList(Score score){
        //当Student_id和Course_id都为空的时候
        if (score.getStudent_id()==0 && score.getCourse_id()==0){
            return scoreDao.findAllScore();
        }
        //当Student_id不为空，而Course_id为空的时候
        if (score.getStudent_id() != 0 && score.getCourse_id() == 0) {
            return scoreDao.findScoreByStudentId(score);
        }
        //当Student_id为空，而Course_id不为空的时候
        if (score.getStudent_id() == 0 && score.getCourse_id() != 0){
            return scoreDao.findScoreByCourseId(score);
        }
        //当Student_id和Course_id都不为空的时候
        if (score.getStudent_id()!=0 && score.getCourse_id()!=0){
            return scoreDao.findScoreByStudentIdAndCourseId(score);
        }
        return null;
    }

    /**
     * 判断是否添加过这条成绩
     * @param score
     * @return
     */
    @Override
    public boolean isAdd(Score score){
        return !scoreDao.isAdd(score).isEmpty();
    }

    /**
     * 更新成绩信息
     * @param id
     * @param score
     * @return
     */
    @Override
    public boolean update(int id,int score){
        int flag = scoreDao.update(id,score);
        return flag!=0;
    }

    /**
     * 按照id删除成绩信息
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id){
        int flag = scoreDao.delete(id);
        return flag!=0;
    }

    /**
     * 返回对应课程的一些成绩信息
     * @param courseId
     * @return
     */
    @Override
    public Map<String,String> getStatsInfo(int courseId){
        List<ResultScore> resultScore = scoreDao.getStatsInfo(courseId);
        Map<String,String> result = new HashMap<>();
        if (resultScore!=null){
            result.put("student_num",""+resultScore.get(0).getStudent_num());
            result.put("max_score",""+resultScore.get(0).getMax_score());
            result.put("min_score",""+resultScore.get(0).getMin_score());
            result.put("mid_score",""+resultScore.get(0).getMid_score());
        }
        return result;
    }
}
