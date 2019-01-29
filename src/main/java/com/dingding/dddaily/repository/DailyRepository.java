package com.dingding.dddaily.repository;

import com.dingding.dddaily.domain.Daily;
import com.dingding.dddaily.domain.DailyCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 钉钉日报接口
 */
public interface DailyRepository extends JpaRepository<Daily,Integer> {
     boolean existsByDaiReportId(String repId);
     Daily findByDaiReportId(String repId);
    int countAllByDaiCreateTimeIsStartingWithAndAndDaiCreatorId(String dateStr,String userId);
int countAllByDaiDateStartingWithAndDaiCreatorId(String dateStr,String userId);
     //Iterable<Daily> findAllByDaiCreateTimeIsStartingWithAndDaiCAndDaiCreatorId(String dataStr,String userId);


}
