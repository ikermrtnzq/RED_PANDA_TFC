<template>
    <div class="tituloContainer">
        <p @click="navegar('/tienda')" class="enlace-volver">
            <span class="material-symbols-outlined"> arrow_back </span>{{ $t('carrito.continuar') }}
        </p>
        <h1 class="">{{ $t('carrito.titulo') }}</h1>
        <p>{{ cantidadProductosCarrito }} {{ $t('carrito.productos-cantidad') }}</p>
    </div>
    <div class="info-container" v-if="!hayProductos">
        <div class="imagen"><img src="/assets/bolsa-de-la-compra.webp" alt="Pedido" /></div>
        <h1>{{ $t('carrito.carrito-vacio') }}</h1>
        <p>{{ $t('carrito.propuesta-añadir') }}</p>
        <button @click="navegar('/tienda')">{{ $t('carrito.ver-menu') }}</button>
    </div>
    <div class="carrito-container" v-else>
        <div class="productos-container">
            <TarjetaProducto
                v-for="p in productosCarrito"
                :key="p.id"
                :p="p"
                @eliminar="manejarAccion('ELIMINAR', p)"
                @sumar="manejarAccion('SUMAR', p)"
                @restar="manejarAccion('RESTAR', p)"
            />
        </div>
        <div class="resumen-container">
            <ResumenPedido
                :subtotal="subtotal"
                :envio="envio"
                :total="total"
                :envioRestante="envioRestante"
                @exito="manejarAccion('EXITO')"
            />
        </div>
    </div>
    <div>
        <AlertaCarrito
            :visible="alerta.visible"
            :titulo="alerta.titulo"
            :mensaje="alerta.mensaje"
            :mostrar-boton="alerta.mostrarBoton"
            :tipo="alerta.tipo"
        />
    </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { computed } from 'vue'
import { storeToRefs } from 'pinia'
import { useCarritoStore, type ProductoCarrito } from '@/stores/counter'
import TarjetaProducto from '@/components/CarritoComp/TarjetaProducto.vue'
import AlertaCarrito from '@/components/AlertaComp/AlertaCarrito.vue'
import { reactive } from 'vue'
import ResumenPedido from '@/components/CarritoComp/ResumenPedido.vue'
import { useI18n } from 'vue-i18n'
import { usePedidoStore } from '@/stores/pedidoStore'
import { useUserStore } from '@/stores/userStore'

const pedidoStore = usePedidoStore()
const userStore = useUserStore()
const carritoStore = useCarritoStore()

const router = useRouter()
const carrito = useCarritoStore()
const { productosCarrito, subtotal, cantidadProductosCarrito, envio, total, envioRestante } =
    storeToRefs(carrito)

const { eliminarProducto, sumarCantidadProducto, restarCantidadProducto } = carrito

const navegar = (path: string) => {
    router.push(path)
}

const alerta = reactive({
    visible: false,
    titulo: '',
    mensaje: '',
    mostrarBoton: false, //Esto es para que al añadir salga un carrito que redirija al carrito :)
    tipo: 1
})

const { t } = useI18n()

const lanzarAlerta = (tipo: String, nombreProducto: String = '') => {
    alerta.visible = true;

    if (tipo == 'ELIMINAR') {
        alerta.titulo = t('alertas.eliminar.titulo')
        alerta.mensaje = `${nombreProducto} ${t('alertas.eliminar.mensaje')}`
        alerta.mostrarBoton = false
        alerta.tipo = 1;
    } else if (tipo == 'EXITO') {
        alerta.titulo = t('alertas.exito.titulo');
        alerta.mensaje = t('alertas.exito.mensaje');
        alerta.mostrarBoton = false;
        alerta.tipo = 1;
    }

    setTimeout(() => {
        alerta.visible = false
        if (tipo == 'EXITO') navegar('/tienda')
    }, 2500) // 2 segundos y medio :)
}
const mandarPedido = async () => {
    console.log(productosCarrito.value)
    //mandamos el pedido al backend, con los productos del carrito, el total y el id del usuario (si está logueado)
    const productos = productosCarrito.value.map((p) => ({
        id: p.id,
        cantidad: p.cantidad,
        nombre: p.nombre,
        precio: p.precio,
        categoria_slug: p.categoria_slug,
        ingredientesQuitados: p.listaIngredientesQuitados,
        ingredientesIDs: p.listaIngredientesIDs,
    }))
    const idUsuario = userStore.id
    const totalPedido = total.value

    await pedidoStore.realizarPedido(idUsuario, totalPedido, productos)
}

const manejarAccion = async (accion: string, p?: ProductoCarrito) => {
    //if (!p) return

    switch (accion) {
        case 'ELIMINAR':
            await eliminarProducto(p!)
            lanzarAlerta(accion, p!.nombre)
            break
        case 'SUMAR':
            await sumarCantidadProducto(p!)
            break
        case 'RESTAR':
            await restarCantidadProducto(p!)
            break
        case 'EXITO':
            await mandarPedido()
            await carritoStore.limpiarTodoElCarrito()
            lanzarAlerta(accion)
            break
    }
}

const hayProductos = computed(() => productosCarrito.value.length > 0)

</script>
<style lang="scss" scoped>
@keyframes slideFromLeft {
    0% {
        opacity: 0;
        transform: translateX(-100px);
    }
    100% {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes slideFromRight {
    0% {
        opacity: 0;
        transform: translateX(100px);
    }
    100% {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes slideFromTop {
    0% {
        opacity: 0;
        transform: translateY(-50px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

.carrito-container {
    display: flex;
    flex-direction: inline;
    gap: 30px;
    margin: 30px 50px 0px 50px;

    margin-top: 50px;

    @include mobile-mediano-down {
        margin: 5px auto;
        margin-left: 10px;
        margin-right: 10px;
        margin-top: 30px;
    }

    @include mobile-down {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    @include desktop-down {
        flex-direction: column;
    }

    .productos-container {
        width: 80%;
        min-width: 30px;
        margin: 0 auto;

        @include desktop-down {
            width: 100%;
            margin: 0 auto;
            justify-content: center;
            align-self: center;
        }
    }

    .resumen-container {
        width: 20%;
        position: sticky;
        top: 120px;
        align-self: flex-start;
        min-width: 300px;
        animation: slideFromRight 0.8s ease-out both;
        display: flex;

        @include desktop-down {
            width: 100%;
            margin: 0 auto;
            justify-content: center;
            align-self: center;
        }
    }
}

.tituloContainer {
    margin-top: 30px;
    margin-left: 50px;

    @include mobile-down {
        margin: 30 auto;
        margin-bottom: 30px;
    }

    > * {
        animation: slideFromTop 0.6s ease-out both;
    }

    @for $i from 1 through 3 {
        > *:nth-child(#{$i}) {
            animation-delay: #{$i * 0.2s};
        }
    }

    .enlace-volver {
        display: flex;
        align-items: center;
        gap: 10px;
        color: $color-blanco-sucioMas;
        font-weight: 500;
        transition: 200ms;
        cursor: pointer;

        &:hover {
            color: $color-texto-blanco;
        }
    }

    h1 {
        padding-top: 10px;
        font-size: 40px;
        color: $color-texto-blanco;
    }

    p {
        color: $color-texto-blanco;
    }
}

.info-container {
    display: flex;
    flex-direction: column;
    height: 80vh;
    justify-content: center;
    align-items: center;

    .imagen {
        border-radius: 50%;
        background-color: $color-rojo-oscuro;
        display: inline-block;

        img {
            padding: 30px;
            display: block;
        }
    }

    h1 {
        padding-top: 30px;
        text-align: center;
    }

    p {
        color: $color-blanco-sucioMas;
        font-weight: 500;
        padding-top: 20px;
        text-align: center;
    }

    button {
        @include boton-rojo();
        margin-top: 40px;
    }
}
</style>
