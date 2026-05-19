<template>
    <div class="mainContainer">
        <EncabezadoInicio />
        <h2 class="h2primero">{{ $t('inicio.razones') }}</h2>
        <div class="contenedor-tarjetas">
            <TarjetaInicio
                v-for="(ventaja, i) in infoVentaja"
                :key="i"
                :imagen="ventaja.imagen"
                :titulo="ventaja.titulo"
                :descripcion="ventaja.descripcion"
            />
        </div>
        <h2>{{ $t('inicio.especialidades') }}</h2>
        <h3>{{ $t('inicio.especialidadesDescripcion') }}</h3>
        <div class="contenedor-tarjetas contenedor-tarjetas-2">
            <TarjetaInicio
                v-for="(especialidad, i) in infoEspecialidades"
                :key="i"
                :imagen="especialidad.imagen"
                :titulo="especialidad.titulo"
                :descripcion="especialidad.descripcion"
            />
        </div>
        <div class="contenedor-listo">
            <h2>{{ $t('inicio.listo') }}</h2>
            <h3>{{ $t('inicio.listoDescripcion') }}</h3>
            <div>
                <button @click="navegar('/tienda')">{{ $t('inicio.botones.verMenu') }}</button>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const router = useRouter()
const menuAbierto = ref(false)
const { t } = useI18n()
const navegar = (path: string) => {
    router.push(path)
    menuAbierto.value = false
}

import EncabezadoInicio from '@/components/InicioComp/EncabezadoInicio.vue'
import TarjetaInicio from '@/components/InicioComp/TarjetaInicio.vue'

const infoVentaja = {
    1: {
        imagen: '/assets/sombrero-de-cocinero.webp',
        titulo: t('inicio.razonesOpciones.titulos.chef'),
        descripcion: t('inicio.razonesOpciones.detalle.chef'),
    },
    2: {
        imagen: '/assets/fuego.webp',
        titulo: t('inicio.razonesOpciones.titulos.ingredientes'),
        descripcion: t('inicio.razonesOpciones.detalle.ingredientes'),
    },
    3: {
        imagen: '/assets/despertador.webp',
        titulo: t('inicio.razonesOpciones.titulos.entrega'),
        descripcion: t('inicio.razonesOpciones.detalle.entrega'),
    },
    4: {
        imagen: '/assets/medalla.webp',
        titulo: t('inicio.razonesOpciones.titulos.calidad'),
        descripcion: t('inicio.razonesOpciones.detalle.calidad'),
    },
}

const infoEspecialidades = {
    1: {
        imagen: '/assets/nigiri.webp',
        titulo: t('inicio.especialidadesOpciones.titulos.sushi'),
        descripcion: t('inicio.especialidadesOpciones.detalle.sushi'),
    },
    2: {
        imagen: '/assets/ramen.webp',
        titulo: t('inicio.especialidadesOpciones.titulos.ramen'),
        descripcion: t('inicio.especialidadesOpciones.detalle.ramen'),
    },
    3: {
        imagen: '/assets/gyoza.webp',
        titulo: t('inicio.especialidadesOpciones.titulos.gyozas'),
        descripcion: t('inicio.especialidadesOpciones.detalle.gyozas'),
    },
    4: {
        imagen: '/assets/cafe-frio.webp',
        titulo: t('inicio.especialidadesOpciones.titulos.bebidas'),
        descripcion: t('inicio.especialidadesOpciones.detalle.bebidas'),
    },
}
</script>
<style lang="scss" scoped>
@keyframes aparecerYSubir {
    0% {
        opacity: 0;
        transform: translateY(150px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes slideFromLeftScroll {
    0% {
        opacity: 0;
        transform: translateX(-150px);
    }
    100% {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes slideFromRightScroll {
    0% {
        opacity: 0;
        transform: translateX(150px);
    }
    100% {
        opacity: 1;
        transform: translateX(0);
    }
}

.mainContainer {
}

h2 {
    text-align: center;
    font-size: clamp(1.8rem, 3vw + 1rem, 2.8rem);
    color: $color-texto-blanco;
    padding-top: 140px;
    animation: slideFromLeftScroll ease-out both;
    animation-timeline: view();
    animation-range: entry 5% cover 25%;
}

h3 {
    text-align: center;
    color: $color-blanco-sucio;
    font-weight: 400;
    padding-top: 15px;
    animation: slideFromRightScroll ease-out both;
    animation-timeline: view();
    animation-range: entry 5% cover 25%;
}

.h2primero {
    padding-top: 100px;
}

.contenedor-tarjetas {
    display: flex;
    flex-wrap: wrap;
    max-width: 1400px;
    justify-content: center;
    margin: 0 auto;
    gap: 40px;
    padding-top: 60px;

    :deep(> *) {
        animation: aparecerYSubir ease-out both;
        animation-timeline: view();
    }

    @for $i from 1 through 10 {
        :deep(> *:nth-child(#{$i})) {
            animation-range: unquote('entry #{5 + ($i * 5)}% cover #{20 + ($i * 5)}%');
        }
    }
}

.contenedor-tarjetas-2 {
    :deep(img) {
        padding: 0px !important;
        background-color: transparent !important;
        border-radius: 0 !important;
        padding-top: 20px !important;
    }

    :deep(.contenedor-tarjeta) {
        &:hover {
            transform: scale(1.05);
        }
    }
}

.contenedor-listo {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    background: $color-rojo-lateral;
    height: 400px;
    margin-top: 70px;
    border-top: 1px solid $color-rojo-oscuro-claro;
    animation: aparecerYSubir ease-out both;
    animation-timeline: view();
    animation-range: entry 5% cover 30%;

    h2 {
        padding-top: 0 !important;
        font-size: clamp(2.2rem, 4vw + 1rem, 3.8rem);
    }

    div {
        display: flex;
        justify-content: center;

        button {
            @include boton-rojo();
            margin-top: 30px;
            width: 250px;
            height: 70px;
            border-radius: 40px;
        }
    }
}
</style>
