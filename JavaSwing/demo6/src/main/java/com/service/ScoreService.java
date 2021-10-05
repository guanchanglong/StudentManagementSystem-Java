package com.service;

import com.entity.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    boolean addScore(Score score);

    List<Score> getScoreList(Score score);

    boolean isAdd(Score score);

    boolean update(int id,int score);

    boolean delete(int id);

    Map<String,String> getStatsInfo(int courseId);
}
