package com.xupt.demo.service.impl;

import com.xupt.demo.dao.ScoreDao;
import com.xupt.demo.entity.ResultScore;
import com.xupt.demo.entity.Score;
import com.xupt.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ScoreServiceImpl")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public int[] getMaxScoreAndMinScoreAdnAveScoreByCourseId(int courseId){
        int[]scoreList = new int[3];
        List<Integer> list = scoreDao.findScoreByCourseId(courseId);
        if (list.isEmpty()){
            return null;
        }else{
            int[]scores = new int[list.size()];
            int sum = 0;
            for(int i=0;i<scores.length;i++){
                scores[i] = list.get(i);
                sum+=scores[i];
            }
            Arrays.sort(scores);
            scoreList[0] = scores[scores.length-1];
            scoreList[1] = scores[0];
            scoreList[2] = sum/scores.length;
            return scoreList;
        }
    }

    @Override
    public int[] getScoreRange(int courseId){
        int[]scoreRange = new int[]{0,0,0,0,0,0};
        List<Integer> list = scoreDao.findScoreByCourseId(courseId);
        if (list.isEmpty()){
            return null;
        }else{
            int sum = 0;
            for(int score:list){
                if (score<60){
                    scoreRange[0]++;
                }else if (score<70){
                    scoreRange[1]++;
                }else if (score<80){
                    scoreRange[2]++;
                }else if (score<90){
                    scoreRange[3]++;
                }else if (score<=100){
                    scoreRange[4]++;
                }
                sum++;
            }
            scoreRange[5] = sum;
            return scoreRange;
        }
    }


}
