export const traducirTexto = async (texto: string, idiomaDestino: string): Promise<string> => {
    if (!texto || idiomaDestino === 'es') return texto; // Si es español, no gastamos API

    try {
        // Formato MyMemory: langpair=es|en para traducir de español a inglés
        const url = `https://api.mymemory.translated.net/get?q=${encodeURIComponent(texto)}&langpair=es|${idiomaDestino}`;
        
        const response = await fetch(url);
        const data = await response.json();

        if (data.responseData && data.responseData.translatedText) {
            return data.responseData.translatedText;
        }
        return texto; // Si falla, devolvemos el original
    } catch (error) {
        console.error("Error en la API de traducción:", error);
        return texto;
    }
};