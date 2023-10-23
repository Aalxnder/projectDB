$(document).ready(function()
{

})

//request
async function registrarUsuarios()
{
    let datos = {};
    datos.nombre = document.getElementById("txtNombre").value;
    datos.apellido = document.getElementById("txtApellido").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;
    datos.rango = document.getElementById("txtRango").value;
    datos.numeroDeIdentificacion = parseInt(document.getElementById("txtNumId").value);
    datos.departamento = document.getElementById("txtDepartamento").value;

    let repetirPassword = document.getElementById("txtRepeatPassword").value;

    if(repetirPassword != datos.password)
    {
        alert("Las contraseñas no coinciden");
        return;
    }
    const request = await fetch("/usuario/registrar",
        {
            method: 'POST',
            headers:getHeaders(),
            body:JSON.stringify(datos)
        });
    alert("Usuario registrado con éxito");
    window.location.href = "/";
}

function getHeaders()
{
    return {
        'Accept':'application/json',
        'content-type':'application/json'
    }
}