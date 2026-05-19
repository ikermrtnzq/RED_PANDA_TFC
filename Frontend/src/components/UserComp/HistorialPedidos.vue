<template>
    <div class="contenedor">
        <h2 class="titulo">{{ $t('historialPedidos.titulo') }}</h2>

        <table class="tabla-pedidos">
            <thead>
                <tr>
                    <th class="titulo-fila"></th>
                    <th class="titulo-fila">{{ $t('historialPedidos.tabla.total') }}</th>
                </tr>
            </thead>
            <tbody class="cuerpo-carta">
                <!--Cada pedido del usuario :)-->
                <template v-for="(pedido, index ) in listaPedidos" :key="pedido.id">
                    <tr
                        class="fila-plato"
                        :class="{ 'fila-activa': pedidoSeleccionadoId === pedido.id }"
                        @click="Detalle(pedido.id)"
                    >
                        <td class="datos">
                            <div class="id">
                                <strong># {{ index + 1 }}</strong>
                            </div>
                        </td>
                        <td class="datos">
                            <div class="precio-total">{{ Number(pedido.precio).toFixed(2) }} €</div>
                        </td>
                    </tr>

                    <!--Esto es lo que se despliega al pulsar sobre un pedido :)-->
                    <tr v-if="pedidoSeleccionadoId === pedido.id" class="fila-detalle">
                        <td colspan="3">
                            <div class="detalle-pedido">
                                <div class="lista-productos">
                                    <div v-for="linea in pedido.lineasPedido" class="item-linea">
                                        <div class="fila-producto">
                                            <div class="info-plato">
                                                <span class="cantidad">{{ linea.cantidad }}x</span>
                                                <span class="nombre">{{ linea.nombrePlato }}</span>
                                                <span v-if="linea.ingredientesQuitados" class="quitados">Sin {{ linea.ingredientesQuitados }}</span>
                                            </div>
                                            <span class="precio-linea">{{ linea.precio }} €</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="resumen-total">
                                    <span>Total pagado:</span>
                                    <strong>{{ Number(pedido.precio).toFixed(2) }} €</strong>
                                </div>
                            </div>
                        </td>
                    </tr>
                </template>
            </tbody>
        </table>

        <div v-if="listaPedidos.length === 0" class="sin-pedidos">
            <p>{{ $t('historialPedidos.detalles.vacio') }}</p>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/userStore'
import { usePedidoStore } from '@/stores/pedidoStore'
import { computed, onMounted, ref } from 'vue'

const userStore = useUserStore()
const pedidoStore = usePedidoStore()

const listaPedidos = computed(() => pedidoStore.pedidos || [])

const pedidoSeleccionadoId = ref<number | null>(null)

const Detalle = (id: number) => {
    pedidoSeleccionadoId.value = pedidoSeleccionadoId.value === id ? null : id
}

onMounted(async () => {
    if (userStore.id) {
        await pedidoStore.obtenerPedidos(userStore.id)
    }
})
</script>

<style lang="scss" scoped>
.contenedor {
    box-sizing: border-box;
    background-color: $color-bg-exterior;
    border: 1px solid $border-color;
    border-radius: 24px;
    padding: 35px 40px;
    max-width: 800px;
    width: 100%;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.8);

    .titulo {
        color: $color-texto-blanco;
        font-size: 24px;
        font-weight: 700;
        margin-bottom: 30px;
        display: flex;
        align-items: center;
        gap: 12px;
    }

    .sin-pedidos {
        text-align: center;
        color: $color-texto-gris;
        padding: 40px 20px;
    }
}

.tabla-pedidos {
    box-sizing: border-box;
    display: block;
    width: 100%;

    thead {
        display: none;
    }

    .cuerpo-carta {
        box-sizing: border-box;
        display: block;
        width: 100%;

        .fila-plato {
            box-sizing: border-box;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: $color-bg-tarjeta;
            border: 1px solid $color-borde-pedido;
            border-radius: 20px;
            padding: 25px 30px;
            margin-bottom: 20px;
            cursor: pointer;
            transition: background-color 0.2s ease;

            &:hover {
                background-color: $color-bg-hover;
            }

            &.fila-activa {
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 0;
                border-bottom: none;
                margin-bottom: 0;
            }

            td {
                box-sizing: border-box;
                display: block;
                border: none;
                padding: 0;
            }

            td:last-child {
                text-align: right;
            }

            .id strong {
                color: $color-texto-blanco;
                font-size: 20px;
                font-weight: 700;
                display: block;
            }

            .id {
                color: $color-texto-gris;
                font-size: 14px;
            }

            .precio-total {
                background-color: $color-verde-bg;
                color: $color-verde-txt;
                border: 1px solid $color-verde-txt;
                padding: 6px 16px;
                border-radius: 20px;
                font-size: 14px;
                font-weight: 600;
                display: inline-block;
            }
        }

        .fila-detalle {
            box-sizing: border-box;
            display: block;
            background-color: $color-bg-tarjeta;
            border-left: 1px solid $color-borde-pedido;
            border-right: 1px solid $color-borde-pedido;
            border-bottom: 1px solid $color-borde-pedido;
            border-bottom-left-radius: 20px;
            border-bottom-right-radius: 20px;
            padding: 0 30px 30px 30px;
            margin-bottom: 20px;

            td {
                box-sizing: border-box;
                display: block;
                padding: 0;
                border: none;
            }

            .detalle-pedido {
                box-sizing: border-box;
                padding-top: 25px;
                border-top: 1px solid $color-borde-pedido;

                .lista-productos {
                    margin-bottom: 25px;

                    .item-linea {
                        margin-bottom: 16px;

                        &:last-child {
                            margin-bottom: 0;
                        }

                        .fila-producto {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;

                            .info-plato {
                                display: flex;
                                gap: 10px;

                                .cantidad {
                                    color: $color-texto-gris;
                                    font-weight: 500;
                                }

                                .nombre {
                                    color: $color-texto-blanco;
                                    font-size: 16px;
                                }

                                .quitados{
                                    color: $color-rojo-panda;
                                    margin-left: 2px;
                                }
                            }

                            .precio-linea {
                                color: $color-texto-gris;
                                font-size: 15px;
                            }
                        }

                        .ingredientes-excluidos {
                            display: flex;
                            flex-wrap: wrap;
                            gap: 8px;
                            margin-top: 8px;
                            padding-left: 25px;

                            .pildora-sin {
                                background-color: $color-pildora-bg;
                                color: $color-pildora-txt;
                                border: 1px solid $color-pildora-borde;
                                padding: 3px 12px;
                                border-radius: 12px;
                                font-size: 12px;
                                font-weight: 500;
                            }
                        }
                    }
                }

                .resumen-total {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    border-top: 1px solid $color-borde-pedido;
                    padding-top: 20px;

                    span {
                        color: $color-texto-blanco;
                        font-size: 18px;
                        font-weight: 700;
                    }

                    strong {
                        color: $color-rojo-total;
                        font-size: 22px;
                        font-weight: 700;
                    }
                }
            }
        }
    }
}

@media (max-width: 600px) {
    .contenedor {
        padding: 20px;
    }
    .tabla-pedidos .cuerpo-carta {
        .fila-plato {
            padding: 20px;
        }
        .fila-detalle {
            padding: 0 20px 20px 20px;
        }
    }
}
</style>
