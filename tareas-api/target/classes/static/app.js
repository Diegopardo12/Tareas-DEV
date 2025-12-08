function cargarTareas() {
    $.ajax({
        url: '/tareas',
        method: 'GET',
        success: function (data) {
            const lista = $('#lista');
            lista.empty();

            data.forEach(function (t) {
                const clase = t.completada ? 'completa' : '';
                const item = $(`
          <li class="${clase}">
            <div class="tarea-info">
              <input type="checkbox" data-id="${t.id}" ${t.completada ? 'checked' : ''} />
              <span>${t.descripcion}</span>
            </div>
            <button class="eliminar" data-id="${t.id}">Eliminar</button>
          </li>
        `);
                lista.append(item);
            });

            // ✅ AQUÍ sí existe data
            actualizarContador(data);
        },
        error: function () {
            mostrarMensaje("❌ Error al cargar tareas", "error");
        }
    });
}

function actualizarContador(tareas) {
    const total = tareas.length;
    const completas = tareas.filter(t => t.completada).length;

    $('#contador').text(`Completadas: ${completas} / ${total}`);
}

function mostrarMensaje(texto, tipo) {
    const msg = $('#mensaje');
    msg.removeClass().addClass('mensaje ' + tipo).text(texto).fadeIn();
    setTimeout(() => msg.fadeOut(), 3000);
}

$(document).ready(function () {

    cargarTareas();

    $('#agregar').click(function () {
        const desc = $('#nueva').val().trim();

        if (!desc) {
            mostrarMensaje("⚠️ Escribe una tarea", "error");
            return;
        }

        $.ajax({
            url: '/tareas',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ descripcion: desc }),
            success: function () {
                $('#nueva').val('');
                mostrarMensaje("✅ Tarea creada correctamente", "ok");
                cargarTareas();
            },
            error: function () {
                mostrarMensaje("❌ Error al crear la tarea", "error");
            }
        });
    });

    $('#lista').on('click', '.eliminar', function () {
        const id = $(this).data('id');

        $.ajax({
            url: '/tareas/' + id,
            method: 'DELETE',
            success: function () {
                mostrarMensaje("🗑️ Tarea eliminada", "ok");
                cargarTareas();
            },
            error: function () {
                mostrarMensaje("❌ Error al eliminar tarea", "error");
            }
        });
    });

    $('#lista').on('change', 'input[type=checkbox]', function () {
        const id = $(this).data('id');

        $.ajax({
            url: '/tareas/' + id,
            method: 'PUT',
            success: function () {
                cargarTareas();
            },
            error: function () {
                mostrarMensaje("❌ Error al actualizar tarea", "error");
            }
        });
    });

});
