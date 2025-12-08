package com.example.tareas.service;

import com.example.tareas.model.Tarea;
import com.example.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository repository;

    public TareaServiceImpl(TareaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tarea crear(Tarea tarea) {
        tarea.setCompletada(false);
        return repository.save(tarea);
    }

    @Override
    public List<Tarea> listar() {
        return repository.findAll();
    }

    @Override
    public Tarea completar(Long id) {
        Tarea tarea = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        tarea.setCompletada(true);
        return repository.save(tarea);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}

