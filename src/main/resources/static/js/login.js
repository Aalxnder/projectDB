$(document).ready(function(){
    actualizarEmailUsuario();
})

async function iniciarSesion()
{
    //remover token existente
    localStorage.removeItem("token");

    let datos = {};
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    const request = await fetch("/usuario/login",
    {
        method: 'POST',
        headers: getHeaders(),
        body: JSON.stringify(datos)
    });
    const respuesta = await request.text();
    if(respuesta != 'FAIL')
    {
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = "/";
    }
    else
    {
        alert("Credenciales incorrectas, por favor intente nuevamente");
    }
}

function getHeaders()
{
    return{
        'Accept':'application/json',
        'content-type':'application/json'
    }
}
function actualizarEmailUsuario()
{
    if(localStorage.email == undefined)
    {
        document.getElementById("txt-email-usuario").outerHTML = "Usuario no registrado";
    }
    document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
}