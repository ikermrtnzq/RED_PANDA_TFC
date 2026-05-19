<template>
    <header>
        <div class="contenedor-1-header">
            <div class="contenedor-imagen-nombre" @click="navegar('/')" style="cursor: pointer">
                <img fetchpriority="high" src="/assets/logo.webp" alt="Logo Profesional" />
                <div class="contenedor-titulo">
                    <h1>{{ $t('header.titulo') }}</h1>
                    <h2>{{ $t('header.descripcion') }}</h2>
                </div>
            </div>
            <nav class="contenedor-enlaces">
                <li
                    v-for="enlace in enlaces"
                    :key="enlace.id"
                    :class="{ active: opcionMenuAbierto === enlace.id }"
                    @click="navegar(enlace.path)"
                >
                    {{ $t(enlace.t) }}
                </li>

                <div
                    class="barra-deslizante"
                    :style="{
                        transform: `translateX(${opcionMenuAbierto * 100}%) translateX(${opcionMenuAbierto * 30}px)`,
                        visibility : opcionMenuAbierto >= 0 ? 'visible' : 'hidden'
                    }"
                ></div>
            </nav>
            <div class="contenedor-carrito-perfil">
                <button v-if="esAdmin" class="admin" @click="navegar('/admin')">
                    <span>AdmMode</span>
                </button>
                <button @click="navegar('/carrito')" class="carrito">
                    <img src="/assets/carrito.webp" alt="icono-carrito" />
                    <span v-if="cantidadProductosCarrito > 0" class="badge-carrito">
                        {{ cantidadProductosCarrito }}
                    </span>
                </button>
                <SelectorIdioma />
                <button v-if="!sesionActiva" class="perfil" @click="navegar('/login')">
                    <img src="/assets/user-icon.webp" alt="icono-user" />{{
                        $t('header.botones.iniciar')
                    }}
                </button>
                <button v-if="sesionActiva" class="perfil" @click="navegar('/perfil')">
                    <img src="/assets/user-icon.webp" alt="icono-user" />{{
                       nombreUsuario
                    }}
                </button>
                <button
                    class="menu-hamburguesa"
                    :class="{ 'is-active': menuAbierto }"
                    @click="menuAbierto = !menuAbierto"
                >
                    <span></span>
                </button>
            </div>
        </div>

        <div class="contenedor-2-header" :class="{ 'esta-abierto': menuAbierto }">
            <template v-for="boton in listaBotonesInicio" :key="boton.id">
                <button
                    v-if="!(tabletMovil && boton.id === 2)"
                    :class="{
                        'is-active2': opcionMenuAbierto === boton.id,
                        none: opcionMenuAbierto === -1,
                    }"
                    @click="navegar(boton.path)"
                >
                    {{ boton.texto }}
                </button>
            </template>
        </div>
    </header>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStore'
import { storeToRefs } from 'pinia'
import { useCarritoStore } from '@/stores/counter'
import SelectorIdioma from './SelectorIdioma.vue'

const userStore = useUserStore()
const esAdmin = computed(() => userStore.esAdmin)
const sesionActiva = computed(() => userStore.sesionActiva)
const nombreUsuario = computed(() => userStore.nombreUsuario);

const route = useRoute()
const router = useRouter()
const menuAbierto = ref(false)
const { t } = useI18n()
const tabletMovil = ref(false)
const carrito = useCarritoStore()
const { cantidadProductosCarrito } = storeToRefs(carrito)

const enlaces = [
    { id: 0, t: 'header.nav.inicio', path: '/' },
    { id: 1, t: 'header.nav.tienda', path: '/tienda' },
]

const opcionMenuAbierto = computed(() => {
    if (route.path === '/inicio') return 0

    if (route.path.includes('/tienda')) return 1

    return -1
})

const listaBotonesInicio = computed(() => [
    { id: 0, texto: t('header.nav.inicio'), path: '/' },
    { id: 1, texto: t('header.nav.tienda'), path: '/tienda' },
    { id: 2, texto: t('header.botones.iniciar'), path: '/login' },
])

const navegar = (path: string) => {
    router.push(path)
    menuAbierto.value = false
}
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

@keyframes slideFromTopLinks {
    0% {
        opacity: 0;
        transform: translateY(-50px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes expandFromCenter {
    0% {
        clip-path: inset(0 50% 0 50%);
        opacity: 0;
    }
    100% {
        clip-path: inset(0 0 0 0);
        opacity: 1;
    }
}

header {
    background-color: $color-fondo-header;
    display: flex;
    flex-direction: column;
    position: sticky;
    top: 0;
    width: 100%;
    z-index: 1000;
    flex-shrink: 0;
    border-bottom: 1px solid $color-rojo-oscuro-claro;

    .contenedor-2-header {
        color: $color-blanco-sucio;
        display: flex;
        flex-direction: column;
        gap: 10px;
        overflow: hidden;
        max-height: 0;
        opacity: 0;
        padding-top: 0;
        padding-bottom: 0;
        margin: 0px 20px;
        border-top: 1px solid transparent;
        transition: all 0.5s ease-in-out;

        &.esta-abierto {
            max-height: 500px;
            opacity: 1;
            padding-top: 20px;
            padding-bottom: 20px;
            margin-top: auto;
            margin-bottom: 20px;
            border-top: 1px solid $color-rojo-oscuro-claro;
        }

        button {
            cursor: pointer;
            padding: 15px;
            border-radius: 10px;
            background: none;
            border-style: none;
            color: $color-blanco-sucio;
            text-align: left;
            font-weight: bold;

            &:hover {
                background-color: $color-azul-sistema;
                transition: 200ms;
            }

            &.is-active2 {
                background-color: $color-rojo-oscuro;
                color: $color-rojo-claro;
            }
        }
    }

    .contenedor-1-header {
        display: flex;
        justify-content: space-around;

        @include mobile-down {
            justify-content: center;
        }

        .contenedor-imagen-nombre {
            display: flex;
            align-items: center;
            transition: transform 0.2s ease;
            animation: slideFromLeft 0.8s ease-out both;

            &:hover {
                transform: scale(1.05) !important;
            }

            img {
                width: 100px;
                height: 80px;

                @include mobile-down {
                    width: 60px;
                    height: auto;
                }
            }

            .contenedor-titulo {
                margin: 20px 0px;

                @include mobile-down {
                    display: none;
                }

                @include mobile-mediano-down {
                    display: none;
                }

                h1 {
                    color: $color-texto-blanco;
                    font-size: clamp(1.8rem, 2.5vw, 2.5rem);

                    @include mobile-down {
                        font-size: 20px;
                    }
                }

                h2 {
                    color: $color-rojo-fuerte-textos;
                    font-size: 13px;
                    font-weight: 400;

                    @include mobile-down {
                        font-size: 9px;
                    }
                }
            }
        }

        .contenedor-enlaces {
            display: flex;
            gap: 30px;
            position: relative;
            align-items: center;
            padding-bottom: 5px;

            @include tablet-down {
                display: none;
            }

            li {
                width: 80px;
                text-align: center;
                list-style: none;
                font-weight: 600;
                cursor: pointer;
                transition: color 0.3s ease;
                color: $color-blanco-sucio;
                animation: slideFromTopLinks 0.6s ease-out both;

                &:hover {
                    color: $color-texto-blanco;
                }

                &.active {
                    color: $color-rojo-panda !important;
                }
            }

            @for $i from 1 through 10 {
                li:nth-of-type(#{$i}) {
                    animation-delay: #{$i * 0.15s};
                }
            }

            .barra-deslizante {
                position: absolute;
                bottom: 0;
                left: 0;
                width: 80px;
                height: 3px;
                background-color: $color-rojo-panda;
                transition: transform 0.3s ease-in-out;
                pointer-events: none;
                animation: expandFromCenter 0.4s ease-out 0.8s both;
            }
        }

        .contenedor-2 {
            li {
                cursor: pointer;
                transition: color 0.2s;

                &:hover {
                    color: $color-rojo-panda;
                }
            }
        }

        .contenedor-carrito-perfil {
            display: flex;
            align-items: center;
            gap: 25px;
            transition: transform 0.3s ease;
            animation: slideFromRight 0.8s ease-out both;

            .carrito {
                background: none;
                border: none;
                position: relative;
                display: flex;
                cursor: pointer;
                transition: transform 0.2s ease;

                &:hover {
                    transform: scale(1.15);

                    img {
                        filter: brightness(1.8);
                    }
                }

                img {
                    width: 25px;
                    height: 25px;
                    filter: brightness(1);
                    transition: filter 0.2s ease;
                }

                .badge-carrito {
                    position: absolute;
                    top: -10px;
                    right: -10px;
                    background-color: $color-rojo-panda;
                    color: white;
                    font-size: 13px;
                    font-weight: bold;
                    width: 22px;
                    height: 22px;
                    border-radius: 50%;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    pointer-events: none;
                }
            }

            .perfil {
                display: flex;
                gap: 10px;
                background: none;
                color: $color-rojo-claro;
                font-weight: 600;
                align-items: center;
                border: 1px solid $color-rojo-fuerte-textos;
                background-color: $color-rojo-oscuro;
                padding: 10px 20px;
                border-radius: 10px;
                transition:
                    transform 0.2s ease,
                    background-color 0.2s ease;

                @include mobile-down {
                    display: none;
                }

                @include movil-grande-down {
                    display: none;
                }

                @include mobile-mediano-down {
                    display: none;
                }

                @include tablet-pequeña-down {
                    display: none;
                }

                img {
                    width: 17px;
                    height: 17px;
                }

                &:hover {
                    transform: scale(1.05);
                    background-color: $color-rojo-oscuro-claro;
                }
            }

            .admin {
                display: flex;
                gap: 10px;
                background: none;
                color: $color-rojo-claro;
                font-weight: 600;
                align-items: center;
                border: 1px solid $color-rojo-fuerte-textos;
                background-color: $color-rojo-oscuro;
                padding: 10px 20px;
                border-radius: 10px;
                transition:
                    transform 0.2s ease,
                    background-color 0.2s ease;

                @include mobile-down {
                    display: none;
                }

                @include movil-grande-down {
                    display: none;
                }

                img {
                    width: 17px;
                    height: 17px;
                }

                &:hover {
                    transform: scale(1.05);
                    background-color: $color-rojo-oscuro-claro;
                }
            }

            .menu-hamburguesa {
                display: none;
            }

            @include tablet-down {
                .menu-hamburguesa {
                    width: 40px;
                    height: 40px;
                    background: transparent;
                    border: none;
                    cursor: pointer;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    position: relative;
                    z-index: 100;

                    span {
                        display: block;
                        width: 30px;
                        height: 3px;
                        background-color: $color-texto-blanco;
                        border-radius: 2px;
                        position: relative;
                        transition: all 0.3s ease;

                        &::before,
                        &::after {
                            content: '';
                            position: absolute;
                            left: 0;
                            width: 30px;
                            height: 3px;
                            background-color: $color-texto-blanco;
                            border-radius: 2px;
                            transition: all 0.3s ease;
                        }

                        &::before {
                            transform: translateY(-8px);
                        }

                        &::after {
                            transform: translateY(8px);
                        }
                    }

                    &.is-active {
                        span {
                            background-color: transparent;

                            &::before {
                                transform: translateY(0) rotate(45deg);
                            }

                            &::after {
                                transform: translateY(0) rotate(-45deg);
                            }
                        }
                    }
                }
            }
        }
    }
}
</style>
