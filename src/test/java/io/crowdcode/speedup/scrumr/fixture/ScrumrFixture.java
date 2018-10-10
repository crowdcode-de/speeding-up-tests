package io.crowdcode.speedup.scrumr.fixture;

import io.crowdcode.speedup.scrumr.model.*;

import java.time.LocalDate;

public class ScrumrFixture {

    public static User defaultUser() {
        return new User()
                .setName("JUNIT")
                .setEmail("user-junit-test@crowdcode.io")
                .setAdmin(false)
                .setPassword("masterkey");
    }

    public static Project defaultProject() {
        return new Project()
                .setName("Speed up Spring Boot Integration Tests")
                .setProductOwner(new User("username", "junit-po@crowdcode.io", "xxx", "po", false))
                .setDescription("Getting hierarchy and hierarchy")
                .setScrumMaster(new User("scrummaster", "junit-sm@crowdcode.io", "xxx", "sm", false));
    }

    public static Sprint defaultSprint() {
        return new Sprint()
                .setName("spring test a")
                .setGoal("getting hierarchy and hierarchy")
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusWeeks(2L))
                .setCapacity(20);
    }

    public static BacklogItem defaultBacklogItem() {
        return new BacklogItem()
                .setTitle("fasten the entity setup")
                .setDescription("what to do to save time")
                .setEstimation(5)
                .setPriority(1);
    }

    public static Task defaultTask() {
        return new Task()
                .setTitle("fasten the entity setup")
                .setDescription("what to do to save time")
                .setEstimation(5)
                .setRemaining(5)
                .setState(TaskState.TODO);
    }
}
