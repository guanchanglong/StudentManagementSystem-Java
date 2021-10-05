package com.xupt.demo.service;

import com.xupt.demo.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    int[] getMaxScoreAndMinScoreAdnAveScoreByCourseId(int courseId);

    int[] getScoreRange(int courseId);
}
