<template>
    <div class="contenedor-info">
        <img src="/assets/logo.webp" alt="Logo Profesional" class="logo" />
        <h2 class="info-titulo">
            {{
                !logueado
                    ? $t('login.info.infoRegistro.bienvenido')
                    : $t('login.info.infoInicio.bienvenido')
            }}
        </h2>
        <p class="info-descrip">
            {{
                !logueado
                    ? $t('login.info.infoRegistro.ingresa')
                    : $t('login.info.infoInicio.ingresa')
            }}
        </p>

        <div v-for="(razon, i) in razones" :key="i" class="contenedor-razones">
            <span class="material-symbols-outlined check"> check </span>
            <div class="contenedor-info-razones">
                <h3 class="razon-titulo">{{ razon.titulo }}</h3>
                <p class="razon-descrip">{{ razon.descrip }}</p>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useI18n } from 'vue-i18n'
const { t } = useI18n()

defineProps<{
    logueado: boolean
}>()

const razones = {
    1: {
        titulo: t('login.info.pedido.titulo'),
        descrip: t('login.info.pedido.descrip'),
    },
    2: {
        titulo: t('login.info.historial.titulo'),
        descrip: t('login.info.historial.descrip'),
    },
    3: {
        titulo: t('login.info.ofertas.titulo'),
        descrip: t('login.info.ofertas.descrip'),
    },
}
</script>

<style scoped lang="scss">
.contenedor-info {
    background: linear-gradient(135deg, #2b1113 0%, #0f0808 100%);
    border-radius: 16px;
    padding: 40px;
    color: $color-texto-blanco;
    max-width: 480px;
    border: 1px solid $color-rojo-oscuro-claro;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;

    .logo {
        width: 100px;
        height: 80px;
        margin-bottom: 30px;
    }

    .info-titulo {
        font-size: 32px;
        font-weight: 800;
        margin: 0 0 12px 0;
    }

    .info-descrip {
        font-size: 18px;
        color: $color-blanco-sucio;
        margin: 0 0 48px 0;
        line-height: 1.4;
    }

    .contenedor-razones {
        display: flex;
        align-items: center;
        gap: 16px;
        margin-bottom: 28px;

        &:last-child {
            margin-bottom: 0;
        }

        .check {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 36px;
            height: 36px;
            background-color: $color-rojo-oscuro;
            color: $color-rojo-fuerte-textos;
            border-radius: 50%;
            font-size: 20px;
            flex-shrink: 0;
        }

        .contenedor-info-razones {
            display: flex;
            flex-direction: column;
            gap: 6px;

            .razon-titulo {
                font-size: 16px;
                font-weight: 700;
                margin: 0;
                color: $color-texto-blanco;
            }

            .razon-descrip {
                font-size: 14px;
                color: $color-blanco-sucioMas;
                margin: 0;
                line-height: 1.4;
            }
        }
    }
}
</style>
