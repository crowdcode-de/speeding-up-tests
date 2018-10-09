package io.crowdcode.speedup.scrumr.model;

import io.crowdcode.speedup.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Sprint extends AbstractEntity {

    @ManyToOne
    private Project project;

    private String name;
    private String goal;

    private Integer capacity;

    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<BacklogItem> backlogItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Task> tasks = new ArrayList<>();


    public Sprint addTask(@NotNull Task task) {
        tasks.add(task);
        return this;
    }

    public Sprint addBacklogItem(@NotNull BacklogItem backlogItem) {
        backlogItems.add(backlogItem);
        return this;
    }
}
