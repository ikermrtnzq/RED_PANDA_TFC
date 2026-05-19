<template>
    <!-- Añadimos ref="dropdownRef" para poder detectar clics fuera y cerrarlo -->
    <div class="selector-idioma" ref="dropdownRef">
        <!-- Botón principal que se muestra siempre -->
        <button class="boton-idioma" @click="menuAbierto = !menuAbierto">
            <div class="info-idioma">
                <img
                    :src="idiomaSeleccionado.icono"
                    :alt="idiomaSeleccionado.nombre"
                    class="bandera"
                />
            </div>
        </button>

        <!-- Lista desplegable -->
        <Transition name="fade-slide">
            <ul v-if="menuAbierto" class="lista-idiomas">
                <li
                    v-for="idioma in idiomas"
                    :key="idioma.codigo"
                    class="opcion-idioma"
                    :class="{ activo: idioma.codigo === idiomaSeleccionado.codigo }"
                    @click="seleccionarIdioma(idioma)"
                >
                    <img :src="idioma.icono" :alt="idioma.nombre" class="bandera" />
                    <span class="nombre">{{ idioma.nombre }}</span>
                </li>
            </ul>
        </Transition>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { useUserStore } from '@/stores/userStore'

const { locale } = useI18n()
const userStore = useUserStore()

interface Idioma {
    codigo: string
    nombre: string
    icono: string
}

const idiomas: Idioma[] = [
    { codigo: 'es', nombre: 'Español', icono: '/assets/banderas/es.png' },
    { codigo: 'en', nombre: 'Inglés', icono: '/assets/banderas/en.png' },
    { codigo: 'zh', nombre: 'Chino', icono: '/assets/banderas/zh.png' },
    { codigo: 'fr', nombre: 'Francés', icono: '/assets/banderas/fr.png' },
    { codigo: 'ja', nombre: 'Japonés', icono: '/assets/banderas/ja.png' },
    { codigo: 'ca', nombre: 'Catalán', icono: '/assets/banderas/ca.png' },
]

const menuAbierto = ref(false)
const dropdownRef = ref<HTMLElement | null>(null)
const idiomaInicial: Idioma = idiomas.find((i) => i.codigo === locale.value) ?? idiomas[0]!
const idiomaSeleccionado = ref<Idioma>(idiomaInicial)

const seleccionarIdioma = (idioma: Idioma) => {
    idiomaSeleccionado.value = idioma
    locale.value = idioma.codigo
    userStore.prefenciaIdioma = idioma.codigo
    localStorage.setItem('idioma-preferido', idioma.codigo)
    menuAbierto.value = false
}

const cerrarAlHacerClicFuera = (event: MouseEvent) => {
    if (dropdownRef.value && !dropdownRef.value.contains(event.target as Node)) {
        menuAbierto.value = false
    }
}

onMounted(() => {
    document.addEventListener('click', cerrarAlHacerClicFuera)
})

onUnmounted(() => {
    document.removeEventListener('click', cerrarAlHacerClicFuera)
})
</script>

<style lang="scss" scoped>
.selector-idioma {
    width: 50px;
}

.boton-idioma {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: fit-content;
    padding: 10px 15px;
    background-color: $color-fondo-tarjeta;
    border: 1px solid $border-color;
    border-radius: 10px;
    color: $color-texto-blanco;
    cursor: pointer;
    transition: all 0.2s ease;

    &:hover {
        background-color: $color-fondo-header;
        border-color: $color-activo;
    }

    .info-idioma {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .flecha {
        width: 16px;
        height: 16px;
        transition: transform 0.3s ease;

        &.rotada {
            transform: rotate(180deg);
        }
    }
}

.bandera {
    width: 22px;
    height: 15px;
    object-fit: cover;
    border-radius: 3px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.5);
}

.nombre {
    font-size: 14px;
    font-weight: 500;
}

.lista-idiomas {
    position: absolute;
    top: calc(100% + 8px);
    left: 0;
    width: 100%;
    background-color: $color-fondo-tarjeta;
    border: 1px solid $border-color;
    border-radius: 10px;
    padding: 5px 0;
    margin: 0;
    list-style: none;
    z-index: 100;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.8);
}

.opcion-idioma {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 10px 15px;
    cursor: pointer;
    color: #a0a0a0;
    transition: all 0.2s;

    &:hover {
        background-color: $color-fondo-header;
        color: $color-texto-blanco;
    }

    &.activo {
        color: $color-texto-blanco;
        background-color: rgba($color-activo, 0.1); // Fondo rojito tenue
        border-left: 3px solid $color-activo;
    }
}

/* Animación bonita al abrir/cerrar */
.fade-slide-enter-active,
.fade-slide-leave-active {
    transition:
        opacity 0.2s ease,
        transform 0.2s ease;
}

.fade-slide-enter-from,
.fade-slide-leave-to {
    opacity: 0;
    transform: translateY(-10px);
}
</style>
