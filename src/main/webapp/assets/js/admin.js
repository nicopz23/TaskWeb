function seleccionarFila(event) {
    // Verificar si el clic ocurrió en una celda de la tabla
    if (event.target.tagName === "TD") {
        var fila = event.target.parentNode;
        var idUsuario = fila.children[0].textContent;
        var nombreUsuario = fila.children[1].textContent;
        var idRol = fila.children[2].textContent;
        var nombreRol = fila.children[3].textContent;

        // Colocar los datos en los campos de texto para editar
        document.getElementById("userId").value = idUsuario;
        document.getElementById("username").value = nombreUsuario;
        document.getElementById("userRole").value = nombreRol;
    }
}

// Agregar evento de clic a la tabla de usuarios
var tablaUsuarios = document.getElementById("tablaUsuarios");
tablaUsuarios.addEventListener("click", seleccionarFila);

