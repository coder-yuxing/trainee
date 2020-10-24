package com.yuxing.trainee.search.domain.repository;

import com.yuxing.trainee.search.domain.entity.EsSupplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSupplierRepositoryTest {

    @Autowired
    private EsSupplierRepository esSupplierRepository;


    @Test
    public void test() {
        EsSupplier esSupplier = new EsSupplier();
        esSupplier.setId(1L);
        esSupplier.setName("测试供应商");
        esSupplier.setEnabled(true);

        EsSupplier.EsContracts esContracts = new EsSupplier.EsContracts("yuxing", "18140000000");
        esSupplier.setContracts(Collections.singletonList(esContracts));

        EsSupplier.ServiceAreas serviceAreas = new EsSupplier.ServiceAreas();
        serviceAreas.setWholeCountry(true);

        esSupplier.setServiceAreas(serviceAreas);
        esSupplierRepository.save(esSupplier);

    }


}