import { ref, computed, watch } from 'vue'
import { defineStore } from 'pinia'
//Servicios del backend
import {obtenerCarrito, annadirCarrito, eliminarDelCarrito, sumarCantidad, restarCantidad, vaciarCarrito } from '@/services/Tienda/CarritoService';
import { useUserStore } from './userStore'


  interface Producto{
    id: number;
    nombre: string;
    precio: number;
    imagen: string;
    descripcion: string;
    categoria_slug: string;
  };

  export interface ProductoCarrito extends Producto {
    cantidad: number;
    listaIngredientesQuitados: string;
    listaIngredientesIDs: number[];
  }

export const useCarritoStore = defineStore('carrito', () => {

  const userStore = useUserStore()
  const productos = ref([])

  const envioMinimoGratis:number = 35;
  const precioEnvio:number = 4.99;

  const carritoGuardado = localStorage.getItem('carrito-redpanda');
  const productosCarrito = ref<ProductoCarrito[]>([]);

  
  const cantidadProductosCarrito = computed(() => productosCarrito.value.length);

  const subtotal = computed(() => {
    return productosCarrito.value.reduce((total, productoCarrito) => total + productoCarrito.precio * (productoCarrito.cantidad ?? 1), 0);
  })

  const envio = computed<number>(() => subtotal.value >= envioMinimoGratis ? 0 : precioEnvio);
  const envioRestante = computed<number>(() => envioMinimoGratis - subtotal.value);

  const total = computed<number>(() => subtotal.value + envio.value);

  //Obtenemos el carrito y lo asignamos a la variable que usamos en este archivo
  const sincronizarCarrito = async () => {
    const datos = await obtenerCarrito();
    productosCarrito.value = datos || [];
  }

  //Añadimos productos a la cesta
  const añadirProducto = async (producto: Producto, ingredientesQuitados?: String, ingredientesIDs?: number[]) => {
    console.log('Añadiendo producto al carrito:', producto);
    console.log('Ingredientes quitados:', ingredientesQuitados);
    console.log('Ingredientes IDs:', ingredientesIDs);
    await annadirCarrito(producto, ingredientesQuitados, ingredientesIDs);
    await sincronizarCarrito();
  }

  //Eliminar un producto del carrito
  
  const eliminarProducto = async (producto: ProductoCarrito) => {
    await eliminarDelCarrito(producto.id, producto);

    productosCarrito.value = productosCarrito.value.filter(
        p => p.id !== producto.id
    );
}

  //Añadimos una unidad de un un producto al carrito
  const sumarCantidadProducto = async (producto: ProductoCarrito) => {
    const carritoActualizado = await sumarCantidad(producto.id, producto);
    if(carritoActualizado) productosCarrito.value = carritoActualizado;
  }

  //Restamos una unidad de un producto del carrito
  const restarCantidadProducto = async (producto: ProductoCarrito) => {
    const carritoActualizado = await restarCantidad(producto.id, producto);
    if(carritoActualizado) productosCarrito.value = carritoActualizado;
  }

  //Vaciamos el carrito
  const limpiarTodoElCarrito = async () => {
    await vaciarCarrito();
    productosCarrito.value = [];
  }

  return {
    // Productos
    productosCarrito,
    
    // Cantidades
    subtotal,
    cantidadProductosCarrito,
    envio,
    total,
    envioRestante,
    
    // Acciones
    añadirProducto,
    eliminarProducto,
    sumarCantidadProducto,
    restarCantidadProducto,
    sincronizarCarrito,
    limpiarTodoElCarrito

  };
});