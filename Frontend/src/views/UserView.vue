<template>
    <div class="contenedor">
        <div class="container-user">
            <userInfo />
        </div>
        <div class="container-historial">
            <HistorialPedidos />
        </div>
    </div>
</template>
<script lang="ts" setup>
import UserInfo from '@/components/UserComp/UserInfo.vue'
import HistorialPedidos from '@/components/UserComp/HistorialPedidos.vue'
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

.contenedor {
    display: flex;
    width: 90vw;
    flex-direction: column;
    align-items: center;
    gap: 30px;
    margin: 40px 25px;

    justify-content: center;

    .container-user {
        animation: slideFromLeft 0.8s ease-out both;
    }

    .container-historial {
        animation: slideFromRight 0.8s ease-out both;
    }
}
</style>
