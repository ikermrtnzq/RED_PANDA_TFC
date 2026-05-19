<template>
    <Transition name="deslizar">
        <div v-if="visible" class="contenedor-alerta">
            <div class="alerta">
                <div class="icono-circulo">
                    <div v-html="eleccionIcono"></div>
                </div>

                <div class="contenido">
                    <h2 class="titulo">{{ titulo }}</h2>
                    <p class="mensaje">{{ mensaje }}</p>

                    <button v-if="mostrarBoton" @click="irACarrito" class="btn-carrito">
                        {{ $t('alertas.verCarrito') }}
                    </button>
                </div>
            </div>
        </div>
    </Transition>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'

const props = defineProps({
    visible: Boolean,
    titulo: String,
    mensaje: String,
    mostrarBoton: Boolean,
    tipo: Number,
})

const emit = defineEmits(['cerrar'])
const router = useRouter()

const exclamacion = '<span class="material-symbols-outlined"> exclamation </span>'
const correcto = '<span class="material-symbols-outlined"> check </span>'

let eleccionIcono = ''

if (props.tipo === 0) eleccionIcono = exclamacion
else if (props.tipo === 1) eleccionIcono = correcto

const irACarrito = () => {
    emit('cerrar')
    router.push('/carrito')
}
</script>

<style lang="scss" scoped>
.contenedor-alerta {
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 1000;
    max-width: 350px;

    &.deslizar-enter-active,
    &.deslizar-leave-active {
        transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
    }
    &.deslizar-enter-from,
    &.deslizar-leave-to {
        opacity: 0;
        transform: translateY(-50px);
    }
    .alerta {
        @include alerta-base;
    }

    .icono-circulo {
        width: 24px;
        height: 24px;
        background-color: #ffffff;
        color: #1a1a1a;
        border-radius: 50%;
        flex-shrink: 0;

        display: flex;
        justify-content: center;
        align-items: center;

        div {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 100%;

            :deep(.material-symbols-outlined) {
                font-size: 16px;
                font-weight: 700; /* Para que el icono se vea un poco más grueso */
                line-height: 1;
            }
        }
    }
}
</style>
