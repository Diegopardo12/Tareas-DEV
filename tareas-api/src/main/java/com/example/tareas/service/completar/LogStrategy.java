package com.example.tareas.service.completar;

import org.springframework.stereotype.Component;

@Component
public class LogStrategy implements CompletarTareaStrategy {

    @Override
    public void ejecutar(Long tareaId) {
        System.out.println("✅ Tarea " + tareaId + " completada");
    }
}
