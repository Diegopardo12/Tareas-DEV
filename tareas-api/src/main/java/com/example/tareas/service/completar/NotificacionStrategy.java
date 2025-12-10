package com.example.tareas.service.completar;

import org.springframework.stereotype.Component;

@Component
public class NotificacionStrategy implements CompletarTareaStrategy {

    @Override
    public void ejecutar(Long tareaId) {
        System.out.println("📩 Notificando tarea completada: " + tareaId);
    }
}
