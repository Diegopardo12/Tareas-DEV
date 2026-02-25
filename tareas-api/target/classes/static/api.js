const API = {
    obtenerTareas() {
        console.log("API: obtenerTareas");
        return $.ajax({ url: '/tareas', method: 'GET' });
    },

    crearTarea(descripcion) {
        console.log("API: crearTarea", descripcion);
        return $.ajax({
            url: '/tareas',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ descripcion })
        });
    },

    completarTarea(id) {
        console.log("API: completarTarea", id);
        return $.ajax({ url: `/tareas/${id}`, method: 'PUT' });
    },

    eliminarTarea(id) {
        console.log("API: eliminarTarea", id);
        return $.ajax({ url: `/tareas/${id}`, method: 'DELETE' });
    }
};
