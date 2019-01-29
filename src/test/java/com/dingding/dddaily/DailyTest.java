package com.dingding.dddaily;

import com.dingding.dddaily.domain.Daily;
import com.dingding.dddaily.domain.Employee;
import com.dingding.dddaily.repository.DailyRepository;
import com.dingding.dddaily.repository.EmployeeRepository;
import com.dingding.dddaily.service.DailyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private DailyService dailyService;




    @Test
    public void addDaily(){
        Daily daily=new Daily();
      //  daily.setDaiSize(100L);

        dailyRepository.save(daily);
    }
}
