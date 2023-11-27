package com.loginapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.loginapp.admin.UserRepository;
import com.loginapp.users.entity.Role;
import com.loginapp.users.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void createuser()
	{
		Role roleAdmin = entityManager.find(Role.class, 1);
		User mok = new User("neowolf4@gmail.com", "Kk126523$", "Mok Zhi Zhuan");
		mok.addRole(roleAdmin);
		User saveduser = repo.save(mok);
		
		assertThat(saveduser.getId()).isGreaterThan(0);	
	}
	
	@Test
	public void createuserwithtworoles()
	{
		User Ravi = new User("ravi222@java.net", "ravi2020", "Ravi");
		Role roleManager = new Role();
		roleManager.setId(2);
		
		Ravi.addRole(roleManager);
		
		User saveduser = repo.save(Ravi);
		
		assertThat(saveduser.getId()).isGreaterThan(0);	
	}
	
	@Test
	public void testListAllusers()
	{
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User usermok = repo.findById(3).get();
		System.out.println(usermok);
		assertThat(usermok).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userNam = repo.findById(3).get();
		userNam.setEmail("neojavaprogrammer@gmail.com");
		
		repo.save(userNam);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 4;
		repo.deleteById(userId);
		
	}
	
	
}
