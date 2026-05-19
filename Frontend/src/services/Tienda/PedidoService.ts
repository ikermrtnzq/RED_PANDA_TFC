export const annadirPedido = async (idUsuario: number, total: number, productos: any[]) => {
    try {
        const res = await fetch(`http://localhost:8081/pedido/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                precio: Number(total), 
                usuario: { id: idUsuario },
                lineasPedido: productos.map(p => ({
                    cantidad: Number(p.cantidad),
                    precio: Number(p.precio),
                    nombrePlato: p.nombre, 
                    categoriaPlato: p.categoria_slug, 
                    ingredientesQuitados: p.listaIngredientesQuitados ,
                    ingredientesIDs: p.listaIngredientesQuitadosIDs
                }))
            }),
            credentials: 'include'
        });
        
        if (!res.ok) {
            const errorData = await res.json();
            throw new Error(errorData.message || 'Error al crear el pedido');
        }

        return await res.json();
    } catch (error) {
        console.error('Error al realizar el pedido:', error);
        throw error;
    }
}

export const obtenerPedidosUsuario = async (idUsuario: number) => {
    try {
        const res = await fetch(`http://localhost:8081/pedidos/${idUsuario}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });
        
        if (!res.ok) {
            const errorData = await res.json();
            throw new Error(errorData.message || 'Error al cargar los pedidos');
        }

        return await res.json();
    } catch (error) {
        console.error('Error al cargar los pedidos:', error);
        throw error;
    }
}

