package com.whiteboard.angularstarter;

import com.whiteboard.angularstarter.api.GenericObject;
import com.whiteboard.angularstarter.api.ObjectController;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObjectController objectController;

    @Test
    public void testFindAllObjects() throws Exception {
        GenericObject genericObject = new GenericObject("Fix test");
        List<GenericObject> allObjects = Collections.singletonList(genericObject);

        given(objectController.getAllObjects()).willReturn(ResponseEntity.ok(allObjects));

        mockMvc.perform(get("/api/objects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void testFindObjectById() throws Exception {
        GenericObject genericObject = new GenericObject();
        genericObject.setId(1L);
        genericObject.setText("Fix test");

        given(objectController.getObjectById(genericObject.getId())).willReturn(ResponseEntity.ok(genericObject));

        mockMvc.perform(get("/api/objects/" + genericObject.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("text", is(genericObject.getText())));
    }
}
