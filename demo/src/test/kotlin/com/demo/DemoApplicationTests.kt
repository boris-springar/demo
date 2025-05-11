package com.demo

import com.demo.controller.RestController
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(RestController::class)
class DemoApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Test
    fun testGetReturns() {
        val result = mockMvc.perform(get("/api/getReturns?traderId=1&playedAmount=5&odd=1.5").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk).andReturn()

        val content = result.response.contentAsString
        val jsonNode=objectMapper.readTree(content)
        val json=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode)
        println(json)
    }

}
