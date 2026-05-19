<template>
    <div class="container-principal">
        <div class="container-info">
            <div><img :src="p.imagen" /></div>

            <div class="container-info-hijo">
                <div class="container-datos">
                    <h3>{{ p.nombre }}</h3>
                    <p>{{ p.descripcion }}</p>
                    <p>{{ p.precio }}€</p>
                </div>
                <div v-if="p.listaIngredientesQuitados" class="container-ingredientes">
                    <p>{{ $t('carrito.ingredientes') }} {{ p.listaIngredientesQuitados }}</p>
                </div>
            </div>
        </div>

        <div class="container-acciones">
            <div class="container-eliminar-añadir">
                <span @click="$emit('restar')" class="material-symbols-outlined"> remove </span>
                <span>{{ p.cantidad }}</span>
                <span @click="$emit('sumar')" class="material-symbols-outlined"> add </span>
            </div>
            <div @click="$emit('eliminar')">
                <span class="material-symbols-outlined">delete</span>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.container-principal {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: $color-fondo-tarjeta;
    border: 1px solid $color-rojo-oscuro;
    border-radius: 12px;
    padding: 12px 20px;
    margin-bottom: 15px;
    transition: 200ms;
    min-width: 300px;

    @include mobile-down {
        flex-direction: column;
    }

    @include desktop-mediano-down {
        flex-direction: column;
    }

    &:hover {
        border: 1px solid $color-rojo-titulo;
    }

    .container-info {
        display: flex;
        align-items: center;
        text-align: center;
        justify-content: space-around;
        gap: 20px;

        @include tablet-pequeña-down {
            flex-direction: column;
        }

        @include desktop-mediano-down {
            flex-direction: column;
        }

        > div:first-child {
            width: 100px;
            height: 100px;
            flex-shrink: 0;

            @include desktop-mediano-down {
                height: 300px;
                width: 300px;
            }

            img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border-radius: 12px;
            }
        }

        .container-info-hijo {
            display: flex;
            gap: 20px;
            align-items: center;

            @include mobile-mediano-down {
                flex-direction: column;
            }

            .container-ingredientes {
                height: fit-content;
                background-color: $color-caca-oscuro;
                border: 1px solid $color-caca;
                border-radius: 8px;
                padding: 10px 16px;
                margin: 0;
                color: $color-amarillo;
                font-size: 14px;
                font-weight: 500;
            }
        }
    }
    .container-datos {
        display: flex;
        flex-direction: column;
        gap: 6px;
        width: 200px;

        h3 {
            margin: 0;
            color: $color-texto-blanco;
            font-size: 1.2rem;
            font-weight: 600;
        }

        p {
            margin: 0;

            &:first-of-type {
                color: $color-blanco-sucioMas;
                font-size: 0.9rem;
            }

            &:last-of-type {
                color: $color-rojo-panda;
                font-weight: bold;
                font-size: 1.1rem;
                margin-top: 4px;
            }
        }
    }

    .container-acciones {
        display: flex;
        align-items: center;
        gap: 20px;
        margin-top: 20px;

        .container-eliminar-añadir {
            display: flex;
            align-items: center;
            background-color: $color-fondo-controles;
            border-radius: 20px;
            padding: 8px 12px;
            gap: 15px;

            span {
                color: $color-texto-blanco;
                user-select: none;

                &.material-symbols-outlined {
                    cursor: pointer;
                    font-size: 1.2rem;
                    color: $color-blanco-sucioMas;
                    transition: color 0.2s;

                    &:hover {
                        color: $color-texto-blanco;
                    }
                }

                &:not(.material-symbols-outlined) {
                    font-weight: 600;
                    min-width: 20px;
                    text-align: center;
                }
            }
        }

        > div:last-child {
            display: flex;
            align-items: center;

            span {
                cursor: pointer;
                color: $color-blanco-sucioMas;
                transition: color 0.2s;

                &:hover {
                    color: $color-rojo-panda;
                }
            }
        }
    }
}
</style>

<script setup lang="ts">
interface ProductoCarrito {
    nombre: string
    descripcion: string
    precio: number
    categoria_slug: string
    imagen: string
    cantidad: number
    listaIngredientesQuitados: string | ''
    listaIngredientesIDs: number[] | []
}

defineProps<{
    p: ProductoCarrito
}>()
</script>
