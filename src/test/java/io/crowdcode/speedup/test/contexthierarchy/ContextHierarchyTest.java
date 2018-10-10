package io.crowdcode.speedup.test.contexthierarchy;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static io.crowdcode.speedup.common.AnsiColor.blue;

@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        {
                @ContextConfiguration(name = "A", classes = AConfiguration.class),
                @ContextConfiguration(name = "B", classes = BConfiguration.class),
                @ContextConfiguration(name = "C", classes = CConfiguration.class),
                @ContextConfiguration(name = "D", classes = DConfiguration.class)
        }
)
public class ContextHierarchyTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void testContextHierarchy() {

        ApplicationContext context = applicationContext;
        do {
            Arrays.stream(context.getBeanDefinitionNames())
                    .map(n->blue(n))
                    .forEach(System.out::println);
            System.out.println("-------------");
            context = context.getParent();
        } while (context != null);

    }
}
