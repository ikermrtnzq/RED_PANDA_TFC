import { defineStore } from 'pinia';
import { ref } from 'vue';
import { annadirPedido, obtenerPedidosUsuario } from '@/services/Tienda/PedidoService';

export const usePedidoStore = defineStore('pedido', () => {
    
    const pedidos = ref<any[]>([]);

    async function realizarPedido(idUsuario: number, total: number, productos: any[]) {
        try {
            const res = await annadirPedido(idUsuario, total, productos);
            return res;
        } catch (error) {
            console.error("Error al realizar pedido:", error);
        }
    }

    async function obtenerPedidos(idUsuario: number) {
        try {
            const res = await obtenerPedidosUsuario(idUsuario);
            pedidos.value = res; 
            return res;
        } catch (error) {
            console.error("Error al obtener pedidos:", error);
            pedidos.value = []; 
        }
    }

    return {
        pedidos, 
        realizarPedido,
        obtenerPedidos
    }
});