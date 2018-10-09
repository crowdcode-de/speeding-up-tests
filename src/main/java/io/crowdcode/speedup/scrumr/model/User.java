package io.crowdcode.speedup.scrumr.model;

import io.crowdcode.speedup.common.jpa.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends AbstractEntity {

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private boolean admin;


}
