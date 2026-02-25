package com.example.tareas.service;

import com.example.tareas.model.Tarea;
import com.example.tareas.repository.TareaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceImplTest {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaServiceImpl tareaService;

    @Test
    void crearTarea_ok() {
        Tarea tarea = new Tarea(null, "Test tarea", false);

        when(tareaRepository.save(any(Tarea.class)))
                .thenReturn(new Tarea(1L, "Test tarea", false));

        Tarea resultado = tareaService.crear(tarea);

        assertNotNull(resultado);
        assertEquals("Test tarea", resultado.getDescripcion());
        assertFalse(resultado.isCompletada());

        verify(tareaRepository, times(1)).save(any(Tarea.class));
    }


}
