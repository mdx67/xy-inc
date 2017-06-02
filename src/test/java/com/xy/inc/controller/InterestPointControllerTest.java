package com.xy.inc.controller;

import com.xy.inc.controller.handle.CustomExceptionHandler;
import com.xy.inc.domain.InterestPoint;
import com.xy.inc.service.InterestPointService;
import com.xy.inc.utils.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author Matheus Xavier
 */
@RunWith(MockitoJUnitRunner.class)
public class InterestPointControllerTest {

    private static List<InterestPoint> interestPoints = Arrays.asList(new InterestPoint(1, "Lanchonete", 27, 12), new InterestPoint(2, "Joalheria", 15, 12));

    private MockMvc mockMvc;

    @Mock
    private InterestPointService interestPointService;

    @InjectMocks
    private InterestPointController interestPointController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(interestPointController).setControllerAdvice(new CustomExceptionHandler()).build();
    }

    @Test
    public void testGetAll() throws Exception {
        when(interestPointService.listAll()).thenReturn(interestPoints);

        mockMvc.perform(get("/poi"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Lanchonete")))
                .andExpect(jsonPath("$[0].x", is(27)))
                .andExpect(jsonPath("$[0].y", is(12)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Joalheria")))
                .andExpect(jsonPath("$[1].x", is(15)))
                .andExpect(jsonPath("$[1].y", is(12)));

        verify(interestPointService, times(1)).listAll();
        verifyNoMoreInteractions(interestPointService);
    }

    @Test
    public void testGetAllEmptyList() throws Exception {
        when(interestPointService.listAll()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get("/poi"))
                .andExpect(status().isNoContent());

        verify(interestPointService, times(1)).listAll();
        verifyNoMoreInteractions(interestPointService);
    }

    @Test
    public void testGetUpcoming() throws Exception {
        when(interestPointService.listUpcoming(20, 10, 10)).thenReturn(interestPoints);

        mockMvc.perform(get("/poi/upcoming").params(buildMapParams()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Lanchonete")))
                .andExpect(jsonPath("$[0].x", is(27)))
                .andExpect(jsonPath("$[0].y", is(12)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Joalheria")))
                .andExpect(jsonPath("$[1].x", is(15)))
                .andExpect(jsonPath("$[1].y", is(12)));

        verify(interestPointService, times(1)).listUpcoming(20, 10, 10);
        verifyNoMoreInteractions(interestPointService);
    }

    private MultiValueMap buildMapParams() {
        MultiValueMap params = new LinkedMultiValueMap<>();
        params.add("x", "20");
        params.add("y", "10");
        params.add("d-max", "10");

        return params;
    }

    @Test
    public void testGetUpcomingEmptyList() throws Exception {
        when(interestPointService.listUpcoming(20, 10, 10)).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(get("/poi/upcoming").params(buildMapParams()))
                .andExpect(status().isNoContent());

        verify(interestPointService, times(1)).listUpcoming(20, 10, 10);
        verifyNoMoreInteractions(interestPointService);
    }

    @Test
    public void testSaveSuccess() throws Exception {
        InterestPoint interestPoint = new InterestPoint("Pub", 12, 8);

        doNothing().when(interestPointService).save(interestPoint);

        mockMvc.perform(post("/poi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.convertToJsonString(interestPoint)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSaveMissingParamX() throws Exception {
        InterestPoint interestPoint = new InterestPoint("Pub", null, 8);

        mockMvc.perform(post("/poi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.convertToJsonString(interestPoint)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"message\":\"Você deve informar a posição X.\"}"));

        verify(interestPointService, times(0)).save(interestPoint);
    }

    @Test
    public void testSaveMissingParamY() throws Exception {
        InterestPoint interestPoint = new InterestPoint("Pub", 12, null);

        mockMvc.perform(post("/poi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.convertToJsonString(interestPoint)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"message\":\"Você deve informar a posição Y.\"}"));

        verify(interestPointService, times(0)).save(interestPoint);
    }
}
