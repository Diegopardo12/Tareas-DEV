// main.js
function mostrarMensaje(texto, tipo) {
    const msg = $('#mensaje');
    msg.stop(true, true);
    msg.removeClass().addClass('mensaje ' + tipo).text(texto).fadeIn(200);
    setTimeout(() => msg.fadeOut(400), 2500);
}

function actualizarContador(tareas) {
    const total = tareas.length;
    const completas = tareas.filter(t => t.completada).length;
    $('#contador').text(`Completadas: ${completas} / ${total}`);
}

function mostrarLoader(on) {
    if (on) $('#loader').show();
    else $('#loader').hide();
}

function crearItemHtml(t) {
    const clase = t.completada ? 'completa' : '';
    return $(`
    <li class="${clase}">
      <div class="tarea-info">
        <input type="checkbox" data-id="${t.id}" ${t.completada ? 'checked' : ''} />
        <span>${t.descripcion}</span>
      </div>
      <button class="eliminar" data-id="${t.id}">Eliminar</button>
    </li>
  `);
}

function cargarTareas() {
    mostrarLoader(true);
    API.obtenerTareas()
        .done(function(data) {
            const lista = $('#lista');
            lista.empty();
            data.forEach(function(t) {
                const item = crearItemHtml(t);
                lista.append(item);
                // animación: pequeña aparición
                item.hide().slideDown(180);
            });
            actualizarContador(data);
        })
        .fail(function() {
            mostrarMensaje("❌ Error al cargar tareas", "error");
        })
        .always(function() {
            mostrarLoader(false);
        });
}

$(document).ready(function () {
    console.log("✅ main.js cargado");
    // asegurar que el botón no haga submit si está dentro de form
    $('#agregar').attr('type', 'button');

    cargarTareas();

    $('#agregar').click(function (e) {
        e.preventDefault();
        const desc = $('#nueva').val().trim();
        if (!desc) {
            mostrarMensaje("⚠️ Escribe una tarea", "error");
            return;
        }

        mostrarLoader(true);
        API.crearTarea(desc)
            .done(function() {
                $('#nueva').val('');
                mostrarMensaje("✅ Tarea creada correctamente", "ok");
                cargarTareas();
            })
            .fail(function() {
                mostrarMensaje("❌ Error al crear la tarea", "error");
            })
            .always(function() {
                mostrarLoader(false);
            });
    });

    $('#lista').on('click', '.eliminar', function () {
        const id = $(this).data('id');
        if (!confirm('¿Eliminar esta tarea?')) return;
        mostrarLoader(true);
        API.eliminarTarea(id)
            .done(function() {
                mostrarMensaje("🗑️ Tarea eliminada", "ok");
                cargarTareas();
            })
            .fail(function() {
                mostrarMensaje("❌ Error al eliminar tarea", "error");
            })
            .always(function() {
                mostrarLoader(false);
            });
    });

    $('#lista').on('change', 'input[type=checkbox]', function () {
        const id = $(this).data('id');
        mostrarLoader(true);
        API.completarTarea(id)
            .done(function() {
                mostrarMensaje("✅ Tarea actualizada", "ok");
                cargarTareas();
            })
            .fail(function() {
                mostrarMensaje("❌ Error al actualizar tarea", "error");
            })
            .always(function() {
                mostrarLoader(false);
            });
    });
});
