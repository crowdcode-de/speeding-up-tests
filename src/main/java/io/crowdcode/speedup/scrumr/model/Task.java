package io.crowdcode.speedup.scrumr.model;


import io.crowdcode.speedup.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Task extends AbstractEntity {

    @NotNull
    @Size(min = 5, max = 1024)
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String description;

    private Integer estimation;
    private Integer remaining;

    @Enumerated(EnumType.STRING)
    private TaskState state;

}
