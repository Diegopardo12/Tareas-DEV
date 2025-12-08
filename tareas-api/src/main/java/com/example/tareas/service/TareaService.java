package com.example.tareas.service;

import com.example.tareas.model.Tarea;
import java.util.List;

public interface TareaService {
    Tarea crear(Tarea tarea);
    List<Tarea> listar();
    Tarea completar(Long id);
    void eliminar(Long id);
}

