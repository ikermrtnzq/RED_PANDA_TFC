<template>
    <div class="contenedor-resumen">
        <div class="resumen">
            <h2>{{ $t('carrito.resumen') }}</h2>
            <div>
                <p>{{ $t('carrito.subtotal') }}</p>
                <p>{{ subtotal.toFixed(2) }}€</p>
            </div>
            <div>
                <p>{{ $t('carrito.envio') }}</p>
                <p :class="{ 'envio-gratuito': envio === 0 }">
                    {{ envio === 0 ? $t('carrito.envioGratis') : envio.toFixed(2) + '€' }}
                </p>
            </div>
            <div>
                <p>{{ $t('carrito.total') }}</p>
                <p>{{ total.toFixed(2) }}€</p>
            </div>
            <div class="texto-aviso" v-if="envioRestante > 0">
                <p>
                    {{
                        $t('carrito.envioGratisAlerta1') +
                        ' ' +
                        envioRestante +
                        '€ ' +
                        $t('carrito.envioGratisAlerta2')
                    }}
                </p>
            </div>
        </div>
        <div class="contenedor-boton-pago">
            <div id="paypal-button-container"></div>
        </div>
    </div>
</template>
<script setup lang="ts">
import { computed, onMounted } from 'vue'
import { loadScript } from '@paypal/paypal-js'
import { useUserStore } from '@/stores/userStore'
import { useRouter } from 'vue-router'
//importamos store de pedido para enviar el pedido al backend
import { usePedidoStore } from '@/stores/pedidoStore'

const userStore = useUserStore()
const sesionActiva = computed(() => userStore.sesionActiva)
const router = useRouter()
const pedidoStore = usePedidoStore()

const props = defineProps<{
    subtotal: number
    envio: number
    total: number
    envioRestante: number
}>()

const emit = defineEmits(['exito'])

const clienteID = 'Ae7XmE263sT54nNN7yXEvSmXkK0zmMFhh8wMiB0b8d1WZRXlIIIm27QdmaazmhcXGVVuJnggDTYd0DsB'

onMounted(async () => {
    try {
        //Esto es para cargar el SDK de PayPal, con el clienteID y la moneda que queremos usar
        //El SDK es necesario para manejar el proceso de pago
        const paypal = await loadScript({
            clientId: clienteID,
            currency: 'EUR',
            disableFunding: 'card',
        })

        //Si el SDK se ha cargado correctamente, renderizamos el botón de PayPal en el contenedor designado
        if (paypal && paypal.Buttons) {
            //Aquí definimos las acciones a realizar en cada etapa del proceso de pago
            await paypal
                .Buttons({
                    onClick: (data, actions) => {
                        if (!userStore.sesionActiva) {
                            router.push('/login')
                            return actions.reject()
                        }
                        return actions.resolve()
                    },
                    //Esta función se ejecuta cuando el usuario hace clic en el botón de PayPal para iniciar el proceso de pago
                    createOrder: (data, actions) => {
                        //Esto es lo que se envía a PayPal, en este caso se le indica el total a pagar que se muestra en el resumen del pedido
                        return actions.order.create({
                            intent: 'CAPTURE', //Esto indica que el pago se capturará inmediatamente después de la aprobación del usuario
                            purchase_units: [
                                {
                                    amount: {
                                        currency_code: 'EUR',
                                        value: props.total.toFixed(2), // El total a pagar, que se muestra en el resumen del pedido
                                    },
                                },
                            ],
                        })
                    },

                    //Si el pago se ha realizado con éxito, se ejecuta
                    onApprove: async (data, actions) => {
                        if (actions.order) {
                            emit('exito') // Lanzamos un mensaje para avisar que se ha dado con exito
                        }
                    },
                    //Si da error en el proceso de pago, se ejecuta
                    onError: (err) => {
                        console.log('ERROR EN EL PROCESO DE PAGO: ' + err)
                    },
                })
                .render('#paypal-button-container') // Aqui  escogemos el contenedor donde se va a renderizar el botón de PayPal
        }
    } catch (error) {
        console.error('No se pudo cargar el botón de PayPal:', error)
    }
})
</script>

<style lang="scss" scoped>
.contenedor-resumen {
    background-color: $color-fondo-tarjeta;
    border: 1px solid rgba(255, 0, 0, 0.15);
    border-radius: 16px;
    padding: 24px;
    color: $color-texto-blanco;
    width: 100%;
    max-width: 380px;
    box-shadow: 0 10px 30px rgba(200, 0, 0, 0.05);
    .resumen {
        display: flex;
        flex-direction: column;

        h2 {
            order: 1;
            font-size: 24px;
            font-weight: 800;
            margin: 0 0 24px 0;
            letter-spacing: 0.5px;
        }

        > div:nth-of-type(1),
        > div:nth-of-type(2) {
            order: 2;
            display: flex;
            justify-content: space-between;
            margin-bottom: 16px;
            font-size: 16px;
            color: $color-blanco-sucio;

            p {
                margin: 0;
            }
        }

        > div:nth-of-type(2) {
            order: 3;
        }

        .texto-aviso {
            order: 4;
            background-color: $color-caca-oscuro;
            border: 1px solid $color-caca;
            border-radius: 8px;
            padding: 14px 16px;
            margin: 8px 0 0 0;

            p {
                margin: 0;
                color: $color-amarillo;
                font-size: 14px;
                font-weight: 500;
            }
        }

        > div:nth-of-type(3) {
            order: 5;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 24px;
            padding-top: 20px;
            border-top: 1px solid rgba(255, 0, 0, 0.2);

            p {
                margin: 0;
                font-size: 22px;
                font-weight: 800;

                &:first-child {
                    color: $color-texto-blanco;
                }

                &:last-child {
                    color: $color-rojo-panda;
                }
            }
        }
    }

    .contenedor-boton-pago {
        margin-top: 24px;
    }
}
</style>
