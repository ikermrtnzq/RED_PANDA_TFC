<template>
    <div class="container">
        <div class="contatiner-1">
            <InfoLogin :logueado="logueado" />
        </div>
        <div >
            <FormLogin :logueado="logueado" @cambiar="cambiarForm()" @exito="mostrarAlerta()"/>
        </div>
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
import InfoLogin from '@/components/LoginComp/InfoLogin.vue'
import FormLogin from '@/components/LoginComp/FormLogin.vue'
import { useUserStore } from '@/stores/userStore'
import { computed } from 'vue'
import { reactive } from 'vue';
import AlertaCarrito from '@/components/AlertaComp/AlertaCarrito.vue'
import { useI18n } from 'vue-i18n'

const userStore = useUserStore()
const logueado = computed(() => userStore.logueado)

const alerta = reactive({
    visible: false,
    titulo: '',
    mensaje: '',
    mostrarBoton: false,
    tipo : 1
});

const { t } = useI18n()
const mostrarAlerta = () => {
    alerta.visible = true;
    alerta.titulo = t('alertas.logueado.titulo');
    alerta.mensaje = t('alertas.logueado.mensaje');
    alerta.tipo = 1;
}
const cambiarForm = ()  => {
    userStore.logueado = !userStore.logueado

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

.container {
    display: flex;
    align-items: stretch;
    gap: 30px;
    margin: 40px 25px;
    justify-content: center;
    flex-wrap: wrap;

    > div:nth-child(odd) {
        animation: slideFromLeft 0.8s ease-out both;
    }

    > div:nth-child(even) {
        animation: slideFromRight 0.8s ease-out both;
    }
}
</style>
