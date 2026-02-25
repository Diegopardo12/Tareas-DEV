package com.example.tareas.service.completar;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StrategyFactory {

    private final Map<String, CompletarTareaStrategy> strategies;

    public StrategyFactory(
            LogStrategy logStrategy,
            NotificacionStrategy notificacionStrategy
    ) {
        strategies = new HashMap<>();
        strategies.put("LOG", logStrategy);
        strategies.put("NOTIFICACION", notificacionStrategy);
    }

    public CompletarTareaStrategy obtener(String tipo) {
        return strategies.getOrDefault(tipo, strategies.get("LOG"));
    }
}
