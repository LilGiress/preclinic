package com.medecineWebApp.Configuration.FakeData;

import com.medecineWebApp.Configuration.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fake")
public class FakeDataController {
    @Autowired
    private FakeDataService fakeDataService;

    @GetMapping("/users")
    public List<User> getFakeUsers(@RequestParam(defaultValue = "10") int count) {
        return fakeDataService.generateFakeUsers(count);
    }
}
