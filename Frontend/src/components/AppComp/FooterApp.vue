<template>
    <footer>
        <div class="contenedor-1-footer">
            <div class="contenedor-1">
                <div class="contenedor-1-imagenNombre">
                    <img
                        src="/assets/logo.webp"
                        style="margin-bottom: 20px"
                        alt="Logo Profesional"
                    />
                    <h2>{{ $t('header.titulo') }}</h2>
                </div>
                <p>{{ $t('footer.descripcion') }}</p>
            </div>
            <div class="contenedor-2">
                <span>{{ $t('footer.titulos.enlaces') }}</span>
                <li @click="navegar('/')" style="cursor: pointer">{{ $t('header.nav.inicio') }}</li>
                <li @click="navegar('/tienda')" style="cursor: pointer">
                    {{ $t('header.nav.tienda') }}
                </li>
            </div>
            <div class="contenedor-3">
                <span>{{ $t('footer.titulos.contacto') }}</span>
                <div>
                    <img src="/assets/marcador.webp" alt="ubicacion" />
                    <p>{{ $t('footer.contacto.calle') }}</p>
                </div>
                <div>
                    <img src="/assets/llamada-telefonica.webp" alt="telefono" />
                    <p>{{ $t('footer.contacto.tlf') }}</p>
                </div>
                <div>
                    <img src="/assets/sobre.webp" alt="email" />
                    <p>{{ $t('footer.contacto.email') }}</p>
                </div>
            </div>
            <div class="contenedor-4">
                <span>{{ $t('footer.titulos.horario') }}</span>
                <p>{{ $t('footer.horario.diario') }}</p>
                <p>{{ $t('footer.horario.findes') }}</p>
                <div class="contenedor-social">
                    <button><img src="/assets/facebook.webp" alt="logoFacebook" /></button>
                    <button><img src="/assets/instagram.webp" alt="logoInstagram" /></button>
                    <button><img src="/assets/gorjeo.webp" alt="logoGorjeo" /></button>
                </div>
            </div>
        </div>
        <div class="contenedor-2-footer">{{ $t('footer.derechos') }}</div>
    </footer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const menuAbierto = ref(false)

const navegar = (path: string) => {
    router.push(path)
    menuAbierto.value = false
}
</script>

<style lang="scss" scoped>
footer {
    background-color: $color-fondo-header;
    color: $color-texto-blanco;
    max-height: 100%;
    border-top: 1px solid $color-rojo-oscuro-claro;

    .contenedor-1-footer {
        display: flex;
        justify-content: center;
        min-width: 600;

        @include tablet-down {
            flex-direction: column;
            align-items: center;
        }

        @include mobile-down {
            flex-direction: column;
            align-items: start;
        }

        & > div {
            width: 300px;
            margin-left: 40px;

            @include mobile-down {
                margin-left: 0px;
            }

            padding-top: 50px;
        }

        div > span {
            font-size: 20px;
            font-weight: 600;
            line-height: 50px;
        }

        .contenedor-1 {
            margin-top: -13px;
            margin: 0 auto;

            img {
                width: 90px;
                height: 70px;
            }

            .contenedor-1-imagenNombre {
                display: flex;
                align-items: center;
            }
        }

        .contenedor-2 {
            list-style: none;
            margin: 0 auto;
            li {
                margin-bottom: 10px;
            }
        }

        .contenedor-3 {
            margin: 0 auto;
            div {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
                gap: 10px;

                p {
                    margin-bottom: auto !important;
                }
            }
        }

        .contenedor-4 {
            margin: 0 auto;
            p {
                margin-bottom: 10px;
            }

            .contenedor-social {
                display: flex;
                gap: 10px;
                button {
                    width: 40px; /* Ancho fijo */
                    height: 40px; /* Alto fijo (igual al ancho) */
                    border-radius: 50%;

                    background: none;
                    border: 1px solid $color-rojo-fuerte-textos;
                    background-color: $color-rojo-oscuro;
                    transition:
                        transform 0.2s ease,
                        background-color 0.2s ease;

                    &:hover {
                        transform: scale(1.05);
                        background-color: $color-rojo-oscuro-claro;
                    }
                }
            }
        }
    }

    .contenedor-2-footer {
        text-align: center;
        margin: 30px 50px 0px 50px;
        border-top: 1px solid $color-rojo-oscuro-claro;
        font-size: 18px;
        color: $color-texto-blanco;
        padding: 30px 0px 50px 0px;

        @include mobile-down {
            margin-left: 0px;
            margin-right: 0px;
        }
    }
}
</style>
