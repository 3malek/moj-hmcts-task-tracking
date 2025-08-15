package me.umalik.hmcts;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerSmokeTest {

    @Autowired
    private TaskController controller;

    @Test
    void loadContext_controllerNotNull() throws Exception
    {
        assertThat( controller ).isNotNull();
    }
}
