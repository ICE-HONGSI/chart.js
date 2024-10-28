//package com.example.demo.repository;
//
//import com.example.demo.entity.Battery;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//
//@Repository
//public interface BatteryMainRepository extends JpaRepository<Battery, Long> {
//    @Query("SELECT new com.example.demo.entity.Battery(b.hour, SUM(b.charge_amount), SUM(b.discharge_amount)) FROM Battery b GROUP BY b.hour ORDER BY b.hour")
//    List<Battery> findSummarizedByHour();
//}
package com.example.demo.main.repository;

import com.example.demo.main.dto.DailyData;
import com.example.demo.main.dto.MonthData;
import com.example.demo.main.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryMainRepository extends JpaRepository<Battery, Integer> {
    // 오늘 날짜에 해당하는 데이터만 조회하는 쿼리
    @Query("SELECT b FROM Battery b WHERE b.year = ?1 AND b.month = ?2 AND b.day = ?3")
    List<Battery> findTodayBatteryData(int year, int month, int day);

    @Query("SELECT new com.example.demo.main.dto.MonthData(b.charge_amount, b.discharge_amount, b.power_generation) FROM Battery b WHERE b.year = :year AND b.month = :month ORDER BY b.day, b.hour")
    List<MonthData> findDailySums(int year, int month);

    @Query("SELECT b FROM Battery b WHERE b.year = :year AND b.month = :month AND b.day = :day ORDER BY b.hour")
    List<Battery> findByDate(int year, int month, int day);

//    @Query("SELECT new com.example.pms.dto.DailyData(b.year, b.month, b.day, AVG(b.charge_amount), AVG(b.discharge_amount), AVG(b.power_generation)) FROM Battery b WHERE b.year = :year AND b.month = :month GROUP BY b.year, b.month, b.day ORDER BY b.day")
//    List<DailyData> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT b FROM Battery b WHERE b.year = :year AND b.month = :month ORDER BY b.day, b.hour")
    List<Battery> findByMonth(@Param("year") int year, @Param("month") int month);
//    @Query("SELECT new com.example.demo.dto.DailyData(b.day, AVG(b.charge_amount), AVG(b.discharge_amount), AVG(b.power_generation)) FROM Battery b WHERE b.year = :year AND b.month = :month ORDER BY b.day, b.hour")
//    List<DailyData> findByYearAndMonth(@Param("year") int year, @Param("month") int month);


}


