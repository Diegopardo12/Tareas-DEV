package com.example.tareas.controller;

import com.example.tareas.model.Tarea;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearTarea_ok() throws Exception {

        Tarea tarea = new Tarea("Tarea de prueba");

        mockMvc.perform(post("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.descripcion").value("Tarea de prueba"))
                .andExpect(jsonPath("$.completada").value(false));
    }

    @Test
    void listarTareas_ok() throws Exception {

        mockMvc.perform(get("/tareas"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void completarTarea_ok() throws Exception {

        String response = mockMvc.perform(post("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descripcion\":\"Tarea a completar\"}"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Tarea tareaCreada = objectMapper.readValue(response, Tarea.class);

        mockMvc.perform(put("/tareas/" + tareaCreada.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarTarea_ok() throws Exception {

        String response = mockMvc.perform(post("/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descripcion\":\"Tarea a eliminar\"}"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Tarea tarea = objectMapper.readValue(response, Tarea.class);

        mockMvc.perform(delete("/tareas/" + tarea.getId()))
                .andExpect(status().isOk());
    }
}
