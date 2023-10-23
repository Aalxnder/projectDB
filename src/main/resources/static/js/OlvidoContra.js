$(document).ready(function()
{

});

async function reiniciarContra()
{
    let datos = {};
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtContra").value;

    let newPassword = document.getElementById("txtNewContra").value;

    //verificar que el email exista en la base de datos
    const validar = await fetch("usuario/validar-email",
        {
            method: 'POST',
            headers:getHeaders(),
            body:JSON.stringify(datos.email)
        });
    const respuesta = await validar.json();
    if(!respuesta.existe)
    {
        alert("El email ingresado no existe");
        return;
    }


    if(newPassword != datos.password)
    {
        alert("Las contraseñas no coinciden");
        return;
    }
    const request = await fetch("usuario/editarContra",
        {
            method: 'POST',
            headers:getHeaders(),
            body:JSON.stringify(datos)
        });
    //cerrar sesion
    localStorage.removeItem("token");
    localStorage.removeItem("email");

    alert("Contraseña cambiada con éxito");
    window.location.href = "index.html";
}
function getHeaders()
{
    return {
        'Accept':'application/json',
        'content-type':'application/json'
    }
}