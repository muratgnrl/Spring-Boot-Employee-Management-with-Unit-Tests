package com.example.auth;

import com.example.auth.model.EmployeeEntity;
import com.example.auth.repository.EmployeeRepository;
import com.example.auth.test.TestCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthApplicationTests implements TestCrud {

	private final EmployeeRepository employeeRepository;

	@Autowired
    AuthApplicationTests(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


	@Test
	@Override
	public void testCreate() {

		EmployeeEntity employeeEntity =EmployeeEntity
				.builder()
				.firstName("Yusuf Test")
				.lastName("GündüzTest")
				.emailId("yusufgnduza@gmail.com")
				.build();
		employeeRepository.save(employeeEntity);
		//Todo nesne null ise assertionError hatası göndersin  1L birinci kayda göre verileri getir
		assertNotNull(employeeRepository.findById(1L).get());

	}

	@Test
	@Override
	public void testList() {

		List<EmployeeEntity> list=employeeRepository.findAll();
		//Todo Eğer sıfırdan büyükse liste vardır.
		assertThat(list).size().isGreaterThan(0);

	}

	@Test
	@Override
	public void testFindById() {

		EmployeeEntity employeeEntity=employeeRepository.findById(1L).get();
		//Todo Yusuf Test adında birisi var mı yok mu.
		assertEquals("Yusuf Test",employeeEntity.getFirstName());


	}

	@Test
	@Override
	public void testUpdate() {

		EmployeeEntity employeeEntity=employeeRepository.findById(1L).get();
		employeeEntity.setFirstName("Yusuf  44 Test");
		employeeRepository.save(employeeEntity);
		//Todo Eşit değilse bir sorun olmayacak,ama eşitse exception fırlatsın
		assertNotEquals("Yusuf Test",employeeRepository.findById(1L).get().getFirstName());
	}



	@Test
	@Override
	public void testDelete() {

		employeeRepository.deleteById(1L);
		//todo İsFalse  yapısı olarak karşımıza geliyo
		assertThat(employeeRepository.existsById(1L)).isFalse();

	}
}
