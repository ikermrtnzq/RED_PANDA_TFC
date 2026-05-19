import { useUserStore } from '@/stores/userStore';

export const obtenerIngredientes = async (Id: number, categoria: string) => {
	const userStore = useUserStore();
    const idioma = userStore.prefenciaIdioma || 'es';

	try {		
		const response = await fetch(`http://localhost:8081/${categoria}/ingredientes/${Id}`);
		if (!response.ok) {
			throw new Error(`Error al obtener los ingredientes: ${response.statusText}`);
		}
		const data = await response.json();
		if (idioma === 'es') return data;
		const ingredientesTraducidos = await Promise.all(
            data.map(async (ingrediente: any) => {
                const nombreTraducido = await traducirConAPI(ingrediente.nombre, idioma);
                
                return {
                    ...ingrediente,
                    nombre: nombreTraducido
                };
            })
        );

        return ingredientesTraducidos;
	} catch (error) {
		console.error(error);
		return [];
	}
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

export const obtenerTodosIngredientes = async () => {
	try {		
		const response = await fetch(`http://localhost:8081/ingrediente`);
		if (!response.ok) {
			throw new Error(`Error al obtener los ingredientes: ${response.statusText}`);
		}
		const data = await response.json();
		return data;
	} catch (error) {
		console.error(error);
		return [];
	}
}

export const annadirIngredientes = async () => {
	try {		
		const response = await fetch(`http://localhost:8081/ingredientecreate`);
		if (!response.ok) {
			throw new Error(`Error al obtener los ingredientes: ${response.statusText}`);
		}
		const data = await response.json();
		return data;
	} catch (error) {
		console.error(error);
		return [];
	}
}