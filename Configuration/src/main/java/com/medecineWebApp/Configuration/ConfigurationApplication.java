package com.medecineWebApp.Configuration;

import com.medecineWebApp.Configuration.models.role.Permission;
import com.medecineWebApp.Configuration.models.role.Roles;
import com.medecineWebApp.Configuration.repository.permission.PermissionRepository;
import com.medecineWebApp.Configuration.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@EnableEurekaServer
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
public class ConfigurationApplication implements CommandLineRunner {
	@Autowired
	private  RoleRepository roleRepository;


	/*	implements CommandLineRunner
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		List<String> strings = Arrays.asList("test", "prot", "pasdort", "press");
		List<String> stringDepart = Arrays.asList("Aerono", "Design", "Logist", "Inform");
		for (int i = 0; i < 2; i++) {
			User user = new User();
			user.setFirstname(faker.name().firstName());
			user.setLastname(faker.name().lastName());
			user.setEmail(faker.internet().emailAddress());
			user.setPassword(faker.internet().password());
			Groupe groupe = new Groupe();
			Random random = new Random();
			String randomString = strings.get(random.nextInt(strings.size()));
			groupe.setNom(randomString);
			groupeRepository.save(groupe);
			//user.setGroups(Set.of(groupe));
			String randomDepart = stringDepart.get(random.nextInt(strings.size()));
			Departement department = new Departement();
			department.setName(randomDepart);
			departmentRepository.save(department);
			//user.setGroups(Set.of(groupe));
			user.setDepartments(List.of(department));
			user.setRoles(Set.of(Roles.class.newInstance()));
			UserRepository.save(user);
		}
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Roles roles = new Roles();
		Permission permission= new Permission();
		permission.setCanApprove(false);
		permission.setCanAssign(true);
		permission.setCanDelete(false);
		permission.setCanCreate(true);
		permission.setCanExport(true);
		permission.setCanGenerateReport(false);
		permission.setCanActivate(false);
		permission.setCanImport(true);
		permission.setCanValidate(false);
		permission.setCanRead(true);
		permission.setCanWrite(true);
		Set<?> perm= new HashSet<>();

		roles.setName("PATIENT");
		roles.setPermissions((Set<Permission>) perm);
		roleRepository.save(roles);
	}
}
