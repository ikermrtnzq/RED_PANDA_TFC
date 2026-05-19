<template>
    <div class="contenedor-encabezado-inicio">
        <h1>
            {{ $t('inicio.descripcion') }}<br /><span>{{ $t('inicio.descripcionSpan') }}</span>
        </h1>
        <p>{{ $t('inicio.descripcionLarga') }}</p>
        <button @click="navegar('/tienda')">
            {{ $t('inicio.botones.pedir') }}<img src="/assets/envio-rapido.webp" alt="Envio" />
        </button>
        <p><img src="/assets/reloj-tres.webp" alt="tiempo" />{{ $t('inicio.tiempoEntrega') }}</p>
    </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()
const menuAbierto = ref(false)
const navegar = (path: string) => {
    router.push(path)
    menuAbierto.value = false
}
</script>

<style lang="scss" scoped>
@keyframes subirOpacidad {
    0% {
        opacity: 0;
        transform: translateY(50px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

.contenedor-encabezado-inicio {
    width: 100%;
    height: 95vh;
    background-image: url('/assets/EncabezadoInicio.webp');
    background-color: rgba(0, 0, 0, 0.7);
    background-repeat: no-repeat;
    background-size: cover;
    background-blend-mode: multiply;
    display: flex;
    flex-direction: column;
    align-items: center;
    vertical-align: middle;
    justify-content: center;

    h1 {
        text-align: center;
        font-size: clamp(2.5rem, 5vw + 1rem, 5rem);
        line-height: 1;
        font-weight: 600;
        animation: subirOpacidad 0.8s ease-out both;

        span {
            color: $color-rojo-titulo;
        }
    }

    p {
        color: $color-blanco-sucio;
        font-size: clamp(1.1rem, 1.5vw + 0.5rem, 2rem);
        text-align: center;
        padding: 40px 0 40px 0;
        animation: subirOpacidad 0.8s ease-out 0.2s both;
    }

    p:last-child {
        font-size: clamp(0.7rem, 1vw + 0.5rem, 1.5rem);
        color: $color-blanco-sucioMas;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 10px;
        animation: subirOpacidad 0.8s ease-out 0.6s both;
    }

    button {
        @include boton-rojo();
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 10px;
        width: 300px;
        height: 100px;
        border-radius: 440px;
        font-size: 25px;
        animation: subirOpacidad 0.8s ease-out 0.4s both;

        &:hover {
            scale: 1.06;
        }
    }
}
</style>
