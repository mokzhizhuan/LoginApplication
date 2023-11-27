package com.loginapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.loginapp.admin.RoleRepository;
import com.loginapp.users.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RolesRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole()
	{
		Role roleAdmin = new Role("Admin");
		Role savedrole = repo.save(roleAdmin);
		
		assertThat(savedrole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateResrtRole()
	{
		Role RoleManager = new Role("Manager");

		
		repo.saveAll(List.of(RoleManager));
	}
}
