package com.revature.workscheduler.controllers;

import com.revature.workscheduler.app.WorkschedulerApplication;
import com.revature.workscheduler.models.Employee;
import com.revature.workscheduler.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(classes= WorkschedulerApplication.class)
public class EmployeeControllerTests
{
	@MockBean
	private EmployeeService service;

	@Autowired
	private MockMvc mvc;

	@Test
	void getEmployeeByID() throws Exception
	{
		Employee expectedEmployee = new Employee(1, "Steve Testingperson","stevet", "parseword", 0);
		Mockito.when(service.get(1))
			.thenReturn(expectedEmployee);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/employees/1"));
		actions.andExpect(MockMvcResultMatchers.status().isOk());
	}
}