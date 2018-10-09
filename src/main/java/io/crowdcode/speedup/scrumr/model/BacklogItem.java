package io.crowdcode.speedup.scrumr.model;


import io.crowdcode.speedup.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class BacklogItem extends AbstractEntity {

    @ManyToOne
    private Project project;

    private String title;
    private String description;
    private Integer priority;
    private Integer estimation;

}
