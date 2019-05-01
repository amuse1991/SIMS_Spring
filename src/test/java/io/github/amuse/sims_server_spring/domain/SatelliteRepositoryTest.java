package io.github.amuse.sims_server_spring.domain;

import io.github.amuse.sims_server_spring.domain.satellite.Satellite;
import io.github.amuse.sims_server_spring.domain.satellite.SatelliteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // 인메모리 DB생성, @entity 클래스만 스캔, @Transactional 어노테이션 포함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //인메모리 DB 사용 안함
public class SatelliteRepositoryTest {
    @Autowired
    SatelliteRepository satelliteRepository;

    @Test
    public void save는_위성정보를_저장한다(){
        //given
        Satellite testSatellite = Satellite.builder()
                .satelliteCode("testSatCode")
                .satelliteName("testSatName")
                .imgSource("testSat.png")
                .launchDate(LocalDateTime.now())
                .build();
        //when
        satelliteRepository.save(testSatellite);

        //then
        Satellite resSat = satelliteRepository.findById("testSatCode").orElseThrow(()-> new EntityNotFoundException());
        assertThat(resSat.getSatelliteCode(),is("testSatCode"));
    }
}
