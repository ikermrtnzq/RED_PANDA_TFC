<template>
    <AdminPanel />
    <AdminForm @actualizar-tabla="manejarAcciones('CREADO')" />
    <AdminCarta :platos="Platos" @actualizar-tabla="manejarAcciones('ELIMINADO')" />

    <AlertaCarrito
        :visible="alerta.visible"
        :titulo="alerta.titulo"
        :mensaje="alerta.mensaje"
        :mostrar-boton="alerta.mostrarBoton"
    />

</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import AdminPanel from "@/components/AdminComp/AdminPanel.vue";
import AdminForm from "@/components/AdminComp/AdminForm.vue";
import AdminCarta from "@/components/AdminComp/AdminCarta.vue";
import { obtenerCartaCompleta, obtenerPlatos } from '@/services/Tienda/PlatosService';
import { reactive } from 'vue';
import AlertaCarrito from '@/components/AlertaComp/AlertaCarrito.vue'
import { useI18n } from 'vue-i18n'
import { watch } from 'vue'
import { useUserStore } from '@/stores/userStore'

const userStore = useUserStore();

const alerta = reactive({
    visible: false,
    titulo: '',
    mensaje: '',
    mostrarBoton: false
});

const Platos = ref([]);
const cargarPlatos = async () => { 
    Platos.value = await obtenerCartaCompleta();
}

const manejarAcciones = (accion: string) => {
    lanzarAlerta(accion);
    cargarPlatos();

}

const { t } = useI18n()
const lanzarAlerta =(accion: string) =>{
    if(accion === 'CREADO'){ 
        alerta.visible = true;
        alerta.titulo = t('alertas.creado.titulo');
        alerta.mensaje = t('alertas.creado.mensaje');
    } else if(accion === 'ELIMINADO') {
        alerta.visible = true;
        alerta.titulo = t('alertas.eliminado.titulo');
        alerta.mensaje = t('alertas.eliminado.mensaje');
    }

    setTimeout(() => {
        alerta.visible = false;
    },2500) // 2 segundos y medio :)
}


onMounted(cargarPlatos);

</script>