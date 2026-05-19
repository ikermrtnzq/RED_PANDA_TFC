<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useCarritoStore } from './stores/counter'
import HeaderApp from './components/AppComp/HeaderApp.vue'
import FooterApp from './components/AppComp/FooterApp.vue'
import { useUserStore } from './stores/userStore'

const carritoStore = useCarritoStore()

const userStore = useUserStore()

const tabletMovil = ref(false)

const revisarTamaño = () => {
    tabletMovil.value = window.innerWidth <= 768 && window.innerWidth > 391
}

onMounted(async () => {
    revisarTamaño()
    window.addEventListener('resize', revisarTamaño)
    
    try {
        console.log('Sincronizando carrito con el servidor...')
        await carritoStore.sincronizarCarrito()
    } catch (error) {
        console.error('Error en la carga inicial:', error)
    }
})

onUnmounted(() => {
    window.removeEventListener('resize', revisarTamaño)
})
</script>

<template>
    <div class="contenedor-principal">
        <header>
            <HeaderApp />
        </header>

        <main class="contenido">
            <RouterView />
        </main>

        <footer>
            <FooterApp />
        </footer>
    </div>
</template>

<style lang="scss" scoped>
.contenedor-principal {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    width: 100%;

    header {
        position: sticky;
        top: 0;
        z-index: 1000;
    }

    main {
        flex: 1;
    }

    footer {
        flex-shrink: 0;
    }
}

main {
    height: 800px;
    background-color: $color-fondo-main;
}
</style>
