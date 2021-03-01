package com.tedeev.test_case.model.repository;

import com.tedeev.test_case.model.entity.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MainRepository extends JpaRepository<MainEntity, Long> {
    @Query(value = "select formid, count(*) as count" +
        " from csv_import" +
        " where formid <> ''" +
        " group by formid" +
        " order by count desc limit 5", nativeQuery = true)
    List<String> getTopFiveForms();


    @Query(value = "select *" +
        " from csv_import" +
        " where subtype <> 'send' " +
        "and subtype <> 'after'" +
        "and subtype <> 'fail'" +
        "and subtype <> 'not-found'"+
        "limit 100", nativeQuery = true)
    List<String> getSecondStatistics();

    @Query(value = "select *" +
        " from csv_import" +
        " where formid <> '' and ts >= (NOW() - INTERVAL '1' HOUR)", nativeQuery = true)
    List<MainEntity> getLastHourUsersAndForms();
}
