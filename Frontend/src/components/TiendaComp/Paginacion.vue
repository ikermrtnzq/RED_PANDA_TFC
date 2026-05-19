<template>
    <div class="paginacion">
        <button v-if="offset > 0" class="paraAtras" @click="anteriores()">Anterior</button>
        <button v-if="!limite" class="paraAdelante" @click="proximos()">Siguiente</button>
    </div>
</template>
<script lang="ts" setup>
//recibimos el offset
defineProps<{
    offset: number
    limite: boolean
}>()

const emit = defineEmits(['cargarMasPlatos', 'cargarMenosPlatos'])

//Mandamos un emit a la tienda para que sepa que tiene que cargar los siguientes o anteriores platos
const proximos = () => {
    emit('cargarMasPlatos')
}
const anteriores = () => {
    emit('cargarMenosPlatos')
}
</script>
<style lang="scss" scoped>
.paginacion {
    display: flex;
    justify-content: center;
    margin: 20px 0;

    .paraAtras,
    .paraAdelante {
        background-color: $color-rojo-panda;
        color: $color-blanco-sucio;
        border: none;
        padding: 10px 20px;
        margin: 0 10px 20px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
        transition: background-color 0.3s ease;

        &:hover {
            background-color: $color-rojo-oscuro;
        }
    }
}
</style>
