<template>
    <div class="tarjeta-perfil">
        <div class="cabecera-avatar">
            <div class="avatar-circulo">
                <span class="material-symbols-outlined" style="font-size: 40px; color: white">
                    person
                </span>
            </div>
            <div class="avatar-textos">
                <h2>{{ nombreUsuario }}</h2>
                <p>{{ emailUsuario }}</p>
            </div>
        </div>

        <div class="grid-campos">
            <div class="campo-bloque">
                <p class="label-texto">{{ $t('usuario.nombre') }}</p>
                <div class="input-falso">
                    <p>{{ nombreUsuario }}</p>
                </div>
            </div>

            <div class="campo-bloque">
                <p class="label-texto">{{ $t('usuario.email') }}</p>
                <div class="input-falso">
                    <p>{{ emailUsuario }}</p>
                </div>
            </div>
        </div>
        <div class="cerrar-container">
            <button class="btn-cerrar" @click="cerrarSesion()">
                {{ $t('usuario.cerrar') }}
            </button>
        </div>
    </div>
</template>
<script setup lang="ts">
import router from '@/router'
import { useUserStore } from '@/stores/userStore'
import { computed } from 'vue'

const userStore = useUserStore()
const nombreUsuario = computed(() => userStore.nombreUsuario)
const emailUsuario = computed(() => userStore.emailUsuario)

const cerrarSesion = async () => {
    userStore.logout()
    router.push('/tienda')
}
</script>
<style lang="scss" scoped>
.tarjeta-perfil {
    background-color: $color-bg-tarjeta;
    border-radius: 20px;
    padding: 40px;
    max-width: 800px;
    width: 100%;
    border-top: 1px solid $color-rojo-oscuro-claro;
    box-shadow:
        0 -15px 30px rgba(255, 0, 0, 0.05),
        0 10px 30px rgba(0, 0, 0, 0.8);

    @include mobile-down {
        padding-left: 10px;
        padding-right: 10px;
    }

    .cabecera-avatar {
        display: flex;
        align-items: center;
        gap: 20px;
        margin-bottom: 40px;

        .avatar-circulo {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background-color: $color-avatar-naranja;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-shrink: 0;
        }

        .avatar-textos {
            h2 {
                color: #ffffff;
                font-size: 28px;
                font-weight: 700;
                margin: 0 0 5px 0;
            }

            p {
                color: $color-texto-secundario;
                font-size: 15px;
                margin: 0;
            }
        }
    }
    .label-texto {
        color: $color-texto-secundario;
        font-size: 14px;
        font-weight: 500;
        margin-bottom: 10px;
        display: flex;
        align-items: center;
        gap: 8px;

        .icono-mundo {
            width: 16px;
            height: 16px;
        }
    }
    .input-falso,
    .desplegable-falso {
        background-color: transparent;
        border: 1px solid $color-borde-input;
        border-radius: 10px;
        padding: 15px 20px;
        color: #ffffff;
        font-size: 16px;
    }
    .grid-campos {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 30px;
        margin-bottom: 30px;
        @media (max-width: 600px) {
            grid-template-columns: 1fr;
        }

        .campo-bloque {
            .input-falso p {
                margin: 0;
            }
        }
    }
    .seccion-idioma {
        margin-bottom: 40px;
        max-width: 400px;

        .desplegable-falso {
            display: flex;
            justify-content: space-between;
            align-items: center;
            cursor: pointer;
            transition: border-color 0.2s;

            &:hover {
                border-color: #444444;
            }

            span strong {
                font-weight: 700;
                margin-right: 5px;
            }

            .flecha {
                width: 18px;
                height: 18px;
                color: $color-texto-secundario;
            }
        }
    }

    .cerrar-container {
        display: flex;
        justify-content: center;
        .btn-cerrar {
            background-color: #2a0808;
            color: #ff3b30;
            border: 1px solid #520c0c;
            border-radius: 10px;
            padding: 12px 30px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.2s ease;

            &:hover {
                background-color: #3d0b0b;
                color: #ffffff;
                border-color: #ff3b30;
            }
        }
    }
}
</style>
