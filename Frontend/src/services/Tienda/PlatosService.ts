import { useUserStore } from '@/stores/userStore';
import { watch } from 'vue';

export const obtenerPlatos = async (categoria?: string, offset?: number) => {
	const userStore = useUserStore();
    const idioma = userStore.prefenciaIdioma || 'es';
	

	console.log("Obteniendo platos de la categoria: ", categoria, " con offset: ", offset);
	const ruta = categoria 
        ? `http://localhost:8081/${categoria}?offset=${offset || 0}` 
        : `http://localhost:8081/carta?offset=${offset || 0}`;
	const res = await fetch(ruta);
	const productos = await res.json();

	if (idioma == 'es') return productos;

	// Si no es español, traducimos los nombres y descripciones antes de devolverlos
    const productosTraducidos = await Promise.all(
        productos.map(async (p: any) => {
            const nombreTraducido = await traducirConAPI(p.nombre, idioma);
            const descTraducida = await traducirConAPI(p.descripcion, idioma);
                
            return {
                ...p,
                nombre: nombreTraducido,
                descripcion: descTraducida
            };
        })
    );
	console.log(productosTraducidos)
	return productosTraducidos;
}

async function traducirConAPI(texto: string, lang: string): Promise<string> {
    if (!texto) return '';
    try {
        const url = `https://api.mymemory.translated.net/get?q=${encodeURIComponent(texto)}&langpair=es|${lang}`;
        const res = await fetch(url);
        const data = await res.json();
        return data.responseData.translatedText || texto;
    } catch {
        return texto;
	}
}

//obtener toda la carta sin paginar
export const obtenerCartaCompleta = async () => {
	const ruta = `http://localhost:8081/cartaCompleta`;
	const res = await fetch(ruta);
	return await res.json();
}

export const eliminarPlato = async (id: number, categoria: string): Promise<boolean> => {
	try {
		const res = await fetch(`http://localhost:8081/${categoria}/delete/${id}`,{
			method: 'DELETE',
			headers: {
                'Content-Type': 'application/json'
            }
		});

		if(!res.ok){
			throw new Error('Error al intentar eliminar el plato de la carta')
		}
		return true
		
	} catch (error) {
		console.error("Error en el servicio de eliminar: ", error);
		return false;
		
	}

}
interface Ingrediente {
    id: number;
    nombre: string;
}

export const annadirPlato = async(nombre: string, descripcion: string, precio: number, cantidad: number, categoria : string, imagen  : string, ingredientes:Ingrediente[] ) =>{
	try {
		const res = await fetch(`http://localhost:8081/${categoria}/create`,{
			method: 'POST',
			headers: {
                'Content-Type': 'application/json'
            },
			body: JSON.stringify({
				nombre: nombre,
				descripcion: descripcion,
				precio: precio,
				cantidad: cantidad,
				imagen: "/assets/carta/"+ categoria + "/" + imagen,
				carta: {id: 1}, 
				ingredientes: ingredientes
			})
		})
		
		if (!res.ok){
			throw new Error('Error al intentar añadir a la carta ')
		}
		
		return true;
	} catch (error) {
		console.error("Error en el servicio de crear: ", error);
		return false
		
	}
}

export const actualizarPlato = async(nombre: string, id: number, descripcion: string, precio: number, cantidad: number, categoria : string, imagen : string ) =>{
	try {
		const res = await fetch(`http://localhost:8081/${categoria}/update/${id}`,{
			method: 'PUT',
			headers: {
                'Content-Type': 'application/json'
            },
			body: JSON.stringify({
				nombre: nombre,
				descripcion: descripcion,
				precio: precio,
				cantidad: cantidad,
				imagen: "/assets/carta/"+ categoria + "/" + imagen

			})
		})
		
		if (!res.ok){
			throw new Error('Error al intentar actualizar plato de la carta ')
		}
		
		return true;
	} catch (error) {
		console.error("Error en el servicio de crear: ", error);
		return false
		
	}
}