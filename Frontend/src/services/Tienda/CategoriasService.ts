export const obtenerCategorias = async () => {
	const res = await fetch("http://localhost:8081/categoria");
	console.log(res);
	return await res.json();
}