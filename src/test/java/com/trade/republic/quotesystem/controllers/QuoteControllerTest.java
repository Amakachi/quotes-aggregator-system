package com.trade.republic.quotesystem.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trade.republic.quotesystem.models.InstrumentDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class QuoteControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Gson gson;

    private MockMvc mockMvc;

    private List<InstrumentDto> reponseInstruments;


    @Before
    public void contextLoads() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
        this.mockMvc = builder.build();

        MvcResult result= mockMvc.perform(get("/api/v1/instruments")
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        reponseInstruments = gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<List<InstrumentDto>>(){}.getType());

    }

    @Test
    public void get_quotes_price_history_by_isin() throws Exception {
        int index = reponseInstruments.size() -  2;
        String isin = reponseInstruments.get(index).getIsin();
        mockMvc.perform(get(String.format("/api/v1/quotes/price-history/%s", isin))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}