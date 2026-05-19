<script setup lang="ts">
    import { onMounted, ref, toRef } from 'vue';
    import { eliminarPlato } from '@/services/Tienda/PlatosService';

    //Recibimos el listado
    const props = defineProps(['platos']);

    //mensaje 
    const emit = defineEmits(['actualizar-tabla']);

    //Lo "clonamos"
    const platos = toRef(props, 'platos');

    //Para borrar y mandar un mensaje al padre para avisar que necesita actualizar
    const borrarPlato = async(id:number, categoria: string) => {
        const respuesta = await eliminarPlato(id, categoria);
        if(respuesta){
            emit('actualizar-tabla');
        }

    }

</script>
<template>
    <div class="gestion-carta">
        <h1 class="titulo-gestion">{{ $t('administrador.modificar.titulo') }}</h1>
        <table class="tabla-carta">
            <thead class="cabecera-carta">
                <tr>
                    <th>{{ $t('administrador.modificar.nombre') }}</th>
                    <th>{{ $t('administrador.modificar.categoria') }}</th>
                    <th>{{ $t('administrador.modificar.precio') }}</th>
                    <th>{{ $t('administrador.modificar.acciones') }}</th>
                </tr>
            </thead>
            <tbody class="cuerpo-carta">
                <tr v-for="plato in platos" :key="plato.id" class="fila-plato">
                    <td>
                        <div class="celda-nombre">
                            <strong>{{ $t(plato.nombre) }}</strong>
                        </div>
                    </td>
                    <td>
                        <div class="celda-categoria">
                            <span>{{ plato.categoria_slug }}</span>
                        </div>
                    </td>
                    <td>
                        <div class="celda-precio">
                            {{ plato.precio }} €
                        </div>
                    </td>
                    <td>
                        <div class="celda-acciones">
                            <button class="btn-borrar" @click="borrarPlato(plato.id, plato.categoria_slug)">🗑️</button>
                        </div>
                    </td>
                    

                </tr>
                
            </tbody>
        </table>
    </div>
</template>
<style lang="scss" scoped>

.gestion-carta {
    background-color: #050505;
    padding: 40px 20px;
    max-width: 1200px;
    margin: 0 auto;
    color: #ffffff;

    .titulo-gestion {
        text-align: center;
        color: $color-rojo-panda;
        margin-bottom: 40px;
        font-weight: 800;
    }
}

.contenedor-tabla {
    background-color: black;
    border: 1px solid $color-rojo-oscuro;
}

.tabla-carta {
    width: 100%;
    border-collapse: collapse; 
    border: $color-rojo-oscuro 1px solid;
    border-radius: 20%;

    
    .cabecera-carta {
        background-color: #121212;
        color: $color-rojo-claro;
        
        th {
            padding: 15px 20px;
            text-align: left;
            border-bottom: 2px solid $color-rojo-oscuro;
        }
    }

    .fila-plato {
        border-bottom: 1px solid rgba($color-rojo-oscuro, 0.3);

        &:hover {
            background-color: rgba($color-rojo-oscuro, 0.1);
        }

        td {
            padding: 15px 20px;
        }

        .celda-acciones {
            display: flex;
            gap: 10px;

            button {
                background: #1a1a1a;
                border: 1px solid #333;
                padding: 8px;
                border-radius: 8px;
                transition: 0.2s;

                &:hover {
                    transform: scale(1.1);
                    background-color: $color-rojo-panda;
                }
            }
        }
    }
}


// Responsive
@media (max-width: 768px) {
    .cabecera-carta { display: none; }
    .fila-plato {
        display: block;
        padding: 10px;
        td {
            display: flex;
            justify-content: space-between;
            padding: 5px 10px;
            border: none;
        }
    }
}
</style>