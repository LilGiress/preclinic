package com.medecineWebApp.Configuration.FakeData;

import com.github.javafaker.Faker;
import com.medecineWebApp.Configuration.models.Departement;
import com.medecineWebApp.Configuration.models.user.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeDataService {
    private final Faker faker;
    public FakeDataService() {
        this.faker = new Faker();
    }

    public List<Users> generateFakeUsers(int count) {
        List<Users> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Users user = new Users();
            user.setFirstname(faker.name().firstName());
            user.setLastname(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setDepartments(List.of(new Departement()));
           // user.setGroups(Set.of(new Groupe()));
            users.add(user);
        }
        return users;
    }
}
