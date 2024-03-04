window.onload=()=>{}
function borrar(idtask){
    var baseUrl = window.location.protocol + "//" + window.location.host + "/TareasWeb/api?idtask="+idtask;

    fetch(baseUrl, {
        method: 'GET', // Puedes cambiar este método según tu necesidad (POST, PUT, DELETE, etc.)
        headers: {
            'Content-Type': 'text', // Puedes ajustar el tipo de contenido según tu necesidad
        },
        // Puedes agregar otros parámetros como body si estás enviando datos
    })
        .then(response => response.text()) // Procesamos la respuesta como JSON
        .then(data => {
            document.getElementById("tablaTareas").innerHTML=data;
        })
        .catch(error => {
            console.error('Error:', error); // Manejamos los errores
        });

}
function editar(idtask){


}


document.addEventListener("DOMContentLoaded", function() {
    var tablaTareas = document.getElementById("tablaTareas");
    var tituloInput = document.getElementById("titulo");
    var descripcionInput = document.getElementById("descripcion");
    var fechaFinalizacionInput = document.getElementById("fechaFinalizacion");
    var estadoInput = document.getElementById("estado");
    var fechaCreacionInput = document.getElementById("fechaCreacion");


    // Evento click en la tabla de tareas
    tablaTareas.addEventListener("click", function(event) {
        if (event.target.tagName === "TD") {
            var fila = event.target.parentNode;

            // Llenar los datos de la tarea
            tituloInput.value = fila.cells[1].textContent;
            descripcionInput.value = fila.cells[2].textContent;
            fechaCreacionInput.value = fila.cells[3].textContent;
            fechaFinalizacionInput.value = fila.cells[4].textContent;
            estadoInput.value = fila.cells[5].textContent;
        }
    });
});
