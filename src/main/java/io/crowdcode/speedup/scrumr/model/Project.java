package io.crowdcode.speedup.scrumr.model;

import io.crowdcode.speedup.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Project extends AbstractEntity {

    @Column(unique = true, length = 200)
    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private User productOwner;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private User scrumMaster;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> developers = new LinkedList<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<BacklogItem> backlogItems = new LinkedList<>();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Sprint> sprints = new LinkedList<>();


}
