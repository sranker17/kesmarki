package com.kesmarki.task;

import com.kesmarki.task.service.AddressService;
import com.kesmarki.task.service.ContactService;
import com.kesmarki.task.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.SessionScope;

import java.util.Objects;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class IntegrationTest {
    @MockBean
    protected PersonService personService;

    @MockBean
    protected AddressService addressService;

    @MockBean
    protected ContactService contactService;

    @Autowired
    private GenericApplicationContext applicationContext;

    private MockHttpSession httpSession;

    protected MockHttpServletRequest httpServletRequest;

    @BeforeEach
    public void setUpSession() {
        ConfigurableBeanFactory factory = applicationContext.getBeanFactory();
        factory.registerScope("session", new SessionScope());
        startHttpSession();
    }

    @AfterEach
    public void tearDown() {
        endHttpSession();
    }

    private void startHttpSession() {
        httpSession = new MockHttpSession();
        startHttpRequest();
    }

    private void endHttpSession() {
        httpSession = null;
        endHttpRequest();
    }

    private void startHttpRequest() {
        httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest));
    }

    private void endHttpRequest() {
        ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
        httpServletRequest = null;
    }
}
