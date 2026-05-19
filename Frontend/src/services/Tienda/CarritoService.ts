export const obtenerCarrito = async () => {
    try{
        const res = await fetch('http://localhost:8081/carrito', {
            method: 'GET',
            credentials: 'include'
        });

        if(!res.ok){
            throw new Error(`Error al obtener el carrito: ${res.statusText}`);
        }

        return await res.json();

    }catch(error){
        console.error("Error al obtener el carrito: ", error);
        return [];

    }
}

export const annadirCarrito = async (producto: any, ingredientesQuitados: string = '',ingredientesIDs: number[] = []) => {
    try {
        const res = await fetch('http://localhost:8081/carrito/agregar', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: producto.id,
                nombre: producto.nombre,
                precio: producto.precio,
                cantidad: producto.cantidad,
                categoria_slug: producto.categoria_slug,
                imagen: producto.imagen,

                listaIngredientesQuitados: ingredientesQuitados,
                listaIngredientesIDs: ingredientesIDs
            })
        });

        if (!res.ok) {
            throw new Error(`Error al agregar al carrito con el servidor: ${res.statusText}`);
        }

        return await res.text();

    } catch(error) {
        console.error("Error en el servicio de agregado al carrito: ", error);
        return null;
    }
}

export const eliminarDelCarrito = async (id: number, producto: any) => {
    try{
        const res = await fetch(`http://localhost:8081/carrito/eliminar/${id}`, {
            method: 'DELETE',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                categoria_slug: producto.categoria_slug
            })
        });

        if (!res.ok) {
            throw new Error(`Error al agregar al carrito con el servidor: ${res.statusText}`);
        }

        return await res.text();


    }
    catch(error){
        console.error("Error en el servicio de agregado al carrito: ", error);
        return null;
    }
}

export const sumarCantidad = async (id: number, producto: any) => {
    try{
        const res = await fetch(`http://localhost:8081/carrito/sumar/${id}`, {
            method: 'PUT',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                categoria_slug: producto.categoria_slug
            })
        });

        if (!res.ok) {
            throw new Error(`Error al agregar al carrito con el servidor: ${res.statusText}`);
        }

        return await res.json();


    }
    catch(error){
        console.error("Error en el servicio de agregado al carrito: ", error);
        return null;
    }
}

export const restarCantidad = async (id: number, producto: any) => {
    try{
        const res = await fetch(`http://localhost:8081/carrito/restar/${id}`, {
            method: 'PUT',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                categoria_slug: producto.categoria_slug
            })
        });

        if (!res.ok) {
            throw new Error(`Error al restar cantidad en el carrito: ${res.statusText}`);
        }

        return await res.json();

    }
    catch(error){
        console.error("Error en el servicio de restar cantidad en el carrito: ", error);
        return null;
    }
}

export const vaciarCarrito = async () => {

    try{
        const res = await fetch("http://localhost:8081/carrito/vaciar", {
            method : 'DELETE',
            credentials: 'include',
        });

        if(!res.ok){
            throw new Error(`Error al vaciar el carrito con el servidor: ${res.statusText}`);
        }  ;

        return await res.text();

    }catch(error){
        console.error("Error en el servicio de vaciado del carrito: ", error);
        return null;
    }

}