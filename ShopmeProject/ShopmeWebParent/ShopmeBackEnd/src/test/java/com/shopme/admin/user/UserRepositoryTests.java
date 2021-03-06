package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void test() {}
	
	@Test
	public void testcreatUser() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user1 = new User("user1@gmail.com", "2020", "Nam", "Ha" );
		user1.AddRole(roleAdmin);
		User savedUser = userRepo.save(user1);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testCreateUser1() {
		User userRavi = new User("ravi@gmial.com", "ravi2020", "Ravi", "Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userRavi.AddRole(roleEditor);
		userRavi.AddRole(roleAssistant);
		
		User savedUser = userRepo.save(userRavi);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testListAllUsers() {
		 Iterable<User> listUsers = userRepo.findAll();
		 listUsers.forEach(user ->System.out.println(user));
	}
	@Test
	public void testGetUserById() {
		User userNam = userRepo.findById(1).get();
		System.out.println(userNam);
		assertThat(userNam).isNotNull();
	}
	@Test
	public void TestUpdateUserDetails() {
		User userNam = userRepo.findById(1).get();
//		userNam.setEnabled(true);
//		userNam.setEmail("namnam@gmail.com");
//		userRepo.save(userNam);
		Role roleEditor = entityManager.find(Role.class, 2);
		userNam.AddRole(roleEditor);

	}
	@Test
	public void testUpdateUserRole() {
		User userRavi = userRepo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSaleperson = new Role(4);
		userRavi.getRoles().remove(roleEditor);
		userRavi.AddRole(roleSaleperson);
		userRepo.save(userRavi);
	}
	
	@Test
	public void testCreateNewUserWithNewRole() {
		User userRavi1 = new User("rav13i@gmial.com", "ravi2020", "Ravi", "Kumar");
		Role roleSaleperson = new Role("Salepersonccdcsd", "cdccddcsd");
		userRavi1.AddRole(roleSaleperson);
		userRepo.save(userRavi1);
	}
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		userRepo.deleteById(userId);
		
	}
	@Test
	public void testUserInfor() {
		User user1 = userRepo.findById(1).get();
		System.out.println(user1);
	}
	@Test
	public void getUserByEmail() {
		String email = "userfake@gmail.com";
		User user = userRepo.getUserByEmail(email);
		assertThat(user).isNotNull();
		
	}
}
