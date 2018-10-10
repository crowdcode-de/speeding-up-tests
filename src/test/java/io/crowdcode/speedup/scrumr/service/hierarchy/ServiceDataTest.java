package io.crowdcode.speedup.scrumr.service.hierarchy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        {
                @ContextConfiguration(classes = TestServiceConfiguration.class),
                @ContextConfiguration(name="scrumr-dta-jpa",classes = TestDataJpaConfiguration.class)
        }
)
public @interface ServiceDataTest {

    /**
     * If SQL output should be logged.
     * @return if SQL is logged
     */
    @PropertyMapping("spring.jpa.show-sql")
    boolean showSql() default true;


}
