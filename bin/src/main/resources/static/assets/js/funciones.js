function eliminarEspecialidad(id) {
    console.log(id);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Está seguro de eliminar la Especialidad?',
        text: "Luego de la eliminacion no se podra utilizar !",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si, Eliminar',
        cancelButtonText: 'No, cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/especialidad/eliminar/" + id,
                success: function (res) {
                    console.log(res);
                }
            });

            swalWithBootstrapButtons.fire({
                title: 'Eliminada!',
                text: 'La especialidad fue eliminada.',
                icon: 'success',
            }).then((ok) => {
                if (ok) {
                    location.href = "/especialidad/listar";
                }

            });
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'La especialidad no fue eliminada :)',
                'error'
            )
        }
    })

}

function eliminarPersona(id) {
    console.log(id);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Está seguro de eliminar esta persona?',
        text: "Luego de la eliminacion no se podra utilizar !",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si, Eliminar',
        cancelButtonText: 'No, cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/persona/eliminar/" + id,
                success: function (res) {
                    console.log(res);
                }
            });

            swalWithBootstrapButtons.fire({
                title: 'Eliminada!',
                text: 'La persona fue eliminada.',
                icon: 'success',
            }).then((ok) => {
                if (ok) {
                    location.href = "/persona/listar";
                }

            });
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'La persona no fue eliminada :)',
                'error'
            )
        }
    })

}

function eliminarEnfermedad(id) {
    console.log(id);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Está seguro de eliminar esta enfermedad?',
        text: "Luego de la eliminacion no se podra utilizar !",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si, Eliminar',
        cancelButtonText: 'No, cancelar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/enfermedad/eliminar/" + id,
                success: function (res) {
                    console.log(res);
                }
            });

            swalWithBootstrapButtons.fire({
                title: 'Eliminada!',
                text: 'La enfermedad fue eliminada.',
                icon: 'success',
            }).then((ok) => {
                if (ok) {
                    location.href = "/enfermedad/listar";
                }

            });
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'La enfermedad no fue eliminada :)',
                'error'
            )
        }
    })

}

function mensajeJs() {
    Swal.fire({
        //position: 'top-end',
        icon: 'success',
        title: 'Consultando datos',
        showConfirmButton: false,
        timer: 1500
    })
}

function mensajeParametro(p) {
    Swal.fire({
        //position: 'top-end',
        icon: 'success',
        title: 'Consultando datos ' + p,
        showConfirmButton: false,
        timer: 1500
    })
}


function buscaPaciente(persona) {

    location.href = "/cita/buscarPaciente/" + persona.identificacion;
    Swal.fire({
        //position: 'top-end',
        icon: 'success',
        title: 'Paciente encontrado en la base',
        showConfirmButton: false,
        timer: 500
    })
}

function grabarUsuario() {
    Swal.fire({
        title: 'Desea guardar los cambios?',
        showDenyButton: true,
        //showCancelButton: true,
        confirmButtonText: 'grabar',
        denyButtonText: `No grabar`,
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            Swal.fire('Saved!', '', 'success')
        } else if (result.isDenied) {
            Swal.fire('Los cambios no fueron grabados', '', 'info')
        }
    })
}

function buscaDiasEspecialidad() {
    const idEspecialidad = document.getElementById('id_especialidad');


    idEspecialidad.addEventListener('change', () => {
        let valorOpcion = idEspecialidad.value;

        //$.getJSON("/cita/cargaEspecialidadesDias", {"espeId": valorOpcion}, function (data)    (ASI ESTABA)
        mensajeJs();
        $.getJSON("/cita/cargaDiasLibresPorEspecialidad", {"espeId": valorOpcion}, function (data) {
            limpiaCombosParaEspecialidad();

            $.each(data, function (i, obj) {

                //$("#id_diasEspe").append("<option value='" + obj.id + "'>" + obj.fecha_dia + "</option>") (SI ESTABA)
                $("#id_diasEspe").append("<option value='" + obj.fecha_dia + "'>" + obj.fecha_dia + "</option>")

            });
        });
    });
}


function limpiaCombosParaFecha() {
    //Limpio el Combo de seleccion de medicos
    $("#id_medico").find('option').remove();
    $("#id_medico").append("<option disabled selected value=\"\">Seleccione.....</option>")
    //Limpio el Combo de seleccion de especialidades
    $("#id_horario_cita").find('option').remove();
    $("#id_horario_cita").append("<option disabled selected value=\"\">Seleccione.....</option>")
}

function buscaMedicosDiasEspecialidad() {
    const idEspecialidad = document.getElementById('id_especialidad');

    const idFechaDia = document.getElementById('id_diasEspe');


    idFechaDia.addEventListener('change', () => {
        let valorEspecialidad = idEspecialidad.value;
        let valorFechaDia = idFechaDia.value;


        $.getJSON("/cita/cargaMedicosPorEspecialidad", {
            "espeId": valorEspecialidad,
            "fechaDia": valorFechaDia
        }, function (data) {
            $("#id_medico").find('option').remove();
            $("#id_medico").append("<option disabled selected value=\"\">Seleccione.....</option>")
            limpiaCombosParaFecha();
            $.each(data, function (i, obj) {

                //$("#id_diasEspe").append("<option value='" + obj.id + "'>" + obj.fecha_dia + "</option>") (SI ESTABA)
                $("#id_medico").append("<option value='" + obj.id + "'>" + obj.nombres + " " + obj.apellidos + "</option>")

            });
        });
    });
}

function limpiaCombosParaEspecialidad() {
    //Limpio el Combo de seleccion de medicos
    $("#id_medico").find('option').remove();
    $("#id_medico").append("<option disabled selected value=\"\">Seleccione.....</option>")

    $("#id_diasEspe").find('option').remove();
    $("#id_diasEspe").append("<option disabled selected value=\"\">Seleccione.....</option>")

    $("#id_horario_cita").find('option').remove();
    $("#id_horario_cita").append("<option disabled selected value=\"\">Seleccione.....</option>")
}

function buscaHorariosEspecialidadDiaMedico() {
    const idEspecialidad = document.getElementById('id_especialidad');
    const idFechaDia = document.getElementById('id_diasEspe');
    const idMedico = document.getElementById('id_medico');


    idMedico.addEventListener('change', () => {
        let valorEspecialidad = idEspecialidad.value;
        let valorFechaDia = idFechaDia.value;
        let valorMedico = idMedico.value;

        $.getJSON("/cita/cargahorariosPorEspecialidadFechaDiaMedico", {
            "espeId": valorEspecialidad,
            "fechaDia": valorFechaDia,
            "idMedico": valorMedico
        }, function (data) {
            $("#id_horario_cita").find('option').remove();
            $("#id_horario_cita").append("<option disabled selected value=\"\">Seleccione.....</option>")

            $.each(data, function (i, obj) {

                //$("#id_diasEspe").append("<option value='" + obj.id + "'>" + obj.fecha_dia + "</option>") (SI ESTABA)
                $("#id_horario_cita").append("<option value='" + obj.id + "'>" + obj.horario + "</option>")

            });
        });
    });
}


function calcularEdad() {
    const fNacimiento = document.getElementById('fecha_nacimiento').value;

    let hoy = new Date();
    let fechaNacimiento = new Date(fNacimiento);

    let edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    let diferenciaMeses = hoy.getMonth() - fechaNacimiento.getMonth();

    if (
        diferenciaMeses < 0 ||
        (diferenciaMeses === 0 && hoy.getDate() < fechaNacimiento.getDate())
    ) {
        edad--
    }

    inputResultado = document.querySelector('#edad_paciente').value = (edad);
}

function obtenerIdPaciente() {


    const idPersona = document.getElementById('id_paciente').value;

    inputIdPersona = document.querySelector('#id_persona').value = idPersona;

}

function guardarCita() {
    const id_persona = document.getElementById('id_paciente');
    const id_especialidad = document.getElementById('id_especialidad');
    const id_fechaDia = document.getElementById('id_diasEspe');
    const id_medico = document.getElementById('id_medico');
    const id_horario = document.getElementById('id_horario_cita');

    let idPersona = id_persona.value;
    let idEspecialidad = id_especialidad.value;
    let idFechaDia = id_fechaDia.value;
    let idMedico = id_medico.value;
    let nombreMedico = id_medico.options[id_medico.selectedIndex].text;
    let idHorario = id_horario.value;

    if (idEspecialidad == -1 || idFechaDia == "" || idMedico == "" || idHorario == "") {
        Swal.fire('Por favor complete todo los datos de la cita');
    } else {
        confirmarCita(idPersona, idEspecialidad, idMedico, nombreMedico, idHorario);
    }
}

function buscaCitaDia(idPersona, idEspecialidad ,idHorario) {
    $.getJSON("/cita/buscarCitaPersonaEspecialidadFecha", {
        "idPersona": idPersona,
        "idEspecialidad": idEspecialidad,
        "idHorario": idHorario
    }, function (data) {

        if(data != null){
            return true;
        }
    });
}


function confirmarCita(idPersona, idEspecialidad, idMedico, nombreMedico, idHorario) {

            Swal.fire({
                title: 'Esta seguro de Agendar esta cita?',
                showDenyButton: true,
                //showCancelButton: true,
                confirmButtonText: 'Si, Agendar',
                denyButtonText: `Cancelar`,
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {

                    $.getJSON("/cita/agendarCita", {
                        "idPersona": idPersona,
                        "idEspecialidad": idEspecialidad,
                        "idMedico": idMedico,
                        "nombreMedico": nombreMedico,
                        "idHorario": idHorario
                    }, function (data)  {
                        ;
                    });

                    Swal.fire('Cita confirmada!', '', 3500, 'success')
                    //location.href = "/cita/listar";



                } else if (result.isDenied) {
                    Swal.fire('Datos de cita no grabados', '', 'info')
                }
            })
}



function cancelaCitaPaciente(id) {
    console.log(id);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Esta seguro de cancelar esta cita?',
        text: 'Luego podra agendar nuevamente otra cita!',
        icon: 'warning',
        confirmButtonColor: '#12960f',
        cancelButtonColor: '#d33',
        showCancelButton: true,
        confirmButtonText: 'Si, cancelar',
        cancelButtonText: 'No, conservar',
        reverseButtons: false
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/cita/cancelarCita/" + id,
                success: function (res) {
                    console.log(res);
                }
            });

            swalWithBootstrapButtons.fire({
                title: 'Eliminada!',
                text: 'La cita fue cancelada.',
                icon: 'success',
            }).then((ok) => {
                if (ok) {
                    location.href = "/cita/listaPaciente";
                }

            });
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'La cita aun se encuentra activa :)',
                'error'
            )
        }
    })
}

function cancelarCitaSecretaria(id) {
    console.log(id);
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButtonColor: '#12960f',
            cancelButtonColor: '#d33',
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Esta seguro de cancelar esta cita?',
        text: 'Luego podra agendar nuevamente otra cita!',
        icon: 'warning',
        showCancelButton: true,
        padding: '0 0 1.25em',
        confirmButtonColor: '#12960f',
        cancelButtonColor: '#d33',
        cancelButtonText: 'No,cancelar',
        confirmButtonText: 'Si,desactivar!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/cita/cancelarCita/" + id,
                success: function (res) {
                    console.log(res);
                }
            });

            swalWithBootstrapButtons.fire({
                title: 'Eliminada!',
                text: 'La cita fue cancelada.',
                icon: 'success',
            }).then((ok) => {
                if (ok) {
                    location.href = "/cita/listar";
                }

            });
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'La cita aun se encuentra activa :)',
                'error'
            )
        }
    })
}

function exportarExcelTipo() {

    const obtenTitulo = document.getElementById('tituloReporte');


    let titulo = obtenTitulo.text

    Swal.fire({
        //position: 'top-end',
        icon: 'success',
        title: 'Consultando datos ' + titulo,
        showConfirmButton: false,
        timer: 1500
    })
}


function guardarCitaPaciente() {
    const id_persona = document.getElementById('id_paciente');
    const id_especialidad = document.getElementById('id_especialidad');
    const id_fechaDia = document.getElementById('id_diasEspe');
    const id_medico = document.getElementById('id_medico');
    const id_horario = document.getElementById('id_horario_cita');

    let idPersona = id_persona.value;
    let idEspecialidad = id_especialidad.value;
    let idFechaDia = id_fechaDia.value;
    let idMedico = id_medico.value;
    let nombreMedico = id_medico.options[id_medico.selectedIndex].text;
    let idHorario = id_horario.value;

    if (idEspecialidad == -1 || idFechaDia == "" || idMedico == "" || idHorario == "") {
        Swal.fire('Por favor complete todo los datos de la cita');
    } else {
        confirmarCita(idPersona, idEspecialidad, idMedico, nombreMedico, idHorario);
    }
}

function confirmarCitaPaciente(idPersona, idEspecialidad, idMedico, nombreMedico, idHorario) {
    Swal.fire({
        title: 'Esta seguro de Agendar esta cita?',
        showDenyButton: true,
        //showCancelButton: true,
        confirmButtonColor: '#12960f',
        denyButtonColor: '#d33',
        confirmButtonText: 'Si, Agendar',
        denyButtonText: 'Cancelar',
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {

            $.getJSON("/cita/agendarCitaPaciente", {
                "idPersona": idPersona,
                "idEspecialidad": idEspecialidad,
                "idMedico": idMedico,
                "nombreMedico": nombreMedico,
                "idHorario": idHorario
            }, function (data) {
                ;
            });

            Swal.fire('Cita confirmada!', '', 3500, 'success')
            location.href = "/cita/listar";

        } else if (result.isDenied) {
            Swal.fire('Datos de cita no grabados', '', 'info')
        }
    })

}


function desactivarHorario(id){

    Swal.fire({
        title: 'Está seguro que desea desactivar el horario?',
        text: "No podrá utilizar el horario luego de esto",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#12960f',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, desactivar!'
    }).then((result) => {
        if (result.isConfirmed) {

            $.getJSON("/cita/buscaCitaPorIdHorario", {
                "idHorario": id
            }, function (data) {



                $.each(data, function (i, obj) {
                    if(obj.id != null){
                        Swal.fire({
                          text:  'El horario no se puede deshabilitar, porque ya tiene citas asignadas!',
                            title: 'Error' ,
                            icon: 'error'
                            }
                        )
                    }
                });
            });

            $.getJSON("/horario/desactivarHorarioPorId", {
                "idHorario": id
            }, function (data) {
                ;
            });

            Swal.fire({
           icon: 'success',
                title: 'Desactivado!',
                text: 'El horario se desactivo con éxito.',
                })
            location.href = "/horario/listaHorariosPlanificados";
        }
    })

}

function enableSearchField(searchType) {
									document.getElementById('btnBuscar')
										.removeAttribute('disabled');
	
							if (searchType === 'buscarCedulaRadio') {
								document.getElementById('buscarPorCedula')
										.removeAttribute('hidden');
								document.getElementById('buscarPorCedula')
										.setAttribute('required', 'true');
								document.getElementById('buscarPorNombre')
										.setAttribute('hidden', 'true');
								document.getElementById('buscarPorNombre')
										.removeAttribute('required');
										
										
								document.querySelector('#labelBusqueda').innerText = 'Cédula';
								document.querySelector('#invalidBuscar').innerText = 'Por favor ingrese cédula a buscar';

							} else {
								document.getElementById('buscarPorNombre')
										.removeAttribute('hidden');
								document.getElementById('buscarPorNombre')
										.setAttribute('required', 'true');
								document.getElementById('buscarPorCedula')
										.setAttribute('hidden', 'true');
								document.getElementById('buscarPorCedula')
										.removeAttribute('required');
								document.querySelector('#labelBusqueda').innerText = 'Nombres';
								document.querySelector('#invalidBuscar').innerText = 'Por favor ingrese nombres a buscar';

							}
}