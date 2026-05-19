<template>
    <div class="overlay-modal" @click.self="$emit('cerrar')">
        <div class="modal-container">
            <div class="modal-header">
                <div class="title-group">
                    <span class="material-symbols-outlined icon-red"> chef_hat </span>
                    <h2>{{ $t('ingredientes.pers') }}</h2>
                </div>
                <span class="material-symbols-outlined close-btn" @click="$emit('cerrar')">
                    close
                </span>
            </div>

            <div class="dish-info">
                <p class="dish-name">{{ nombre }}</p>
                <p class="instruction">{{ $t('ingredientes.select') }}</p>
            </div>

            <div class="contenedor-ingredientes">
                <label
                    v-for="ingrediente in ingredientes"
                    :key="ingrediente.id"
                    class="opcion-ingrediente"
                    :for="'ingrediente-' + ingrediente.id"
                >
                    <span class="ingrediente-nombre">{{ ingrediente.nombre }}</span>

                    <input
                        type="checkbox"
                        :id="'ingrediente-' + ingrediente.id"
                        v-model="ingredientesSeleccionados"
                        :value="ingrediente.id"
                    />
                </label>
            </div>

            <div class="modal-footer">
                <div class="price-group">
                    <p class="price-label">{{ $t('ingredientes.precio') }}</p>
                    <p class="price-amount">€{{ precio }}</p>
                </div>
                <div class="action-buttons">
                    <button class="btn-cancel" @click="$emit('cerrar')">
                        {{ $t('ingredientes.cancel') }}
                    </button>
                    <button @click="añadirAlCarrito()" class="btn-add">
                        {{ $t('ingredientes.añadir') }}
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'

interface Ingrediente {
    id: number
    nombre: string
}

const props = defineProps<{
    nombre: string
    precio: number
    ingredientes: Ingrediente[]
}>()

const emit = defineEmits(['cerrar', 'añadir'])
const ingredientesSeleccionados = ref<number[]>([])

//al inicio están todos los ingredientes en el array, para que así aparezcan todos seleccionados
watch(() => props.ingredientes, (nuevosIngredientes) =>{
    if (nuevosIngredientes && nuevosIngredientes.length > 0) {
        ingredientesSeleccionados.value = nuevosIngredientes.map(ing => ing.id);
    }
}, {immediate : true});

const añadirAlCarrito = () => {

    //Revisamos cuales son los que ha quitado el usuario en comparacion con la lista de ingredientes inicial que habia
    const quitados = props.ingredientes.filter((ingrediente) => !ingredientesSeleccionados.value.includes(ingrediente.id));

    //Obtenemos sus nombres
    const nombresQuitados = quitados.map((ingrediente)=> ingrediente.nombre).join(',');

    //obtenemos sus IDs
    const idsQuitados = quitados.map((ingrediente) => ingrediente.id);

    emit('añadir', {
        // Mandamos lo que el usuario ha decidido quitar
        ingredientesIDs: JSON.stringify(idsQuitados), 
        listaIngredientesQuitados: nombresQuitados,
    })
}
</script>

<style scoped lang="scss">
@keyframes slideFromBottomContainer {
    0% {
        opacity: 0;
        transform: translateY(100px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes slideFromTop {
    0% {
        opacity: 0;
        transform: translateY(-40px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes slideFromLeft {
    0% {
        opacity: 0;
        transform: translateX(-50px);
    }
    100% {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes slideFromRight {
    0% {
        opacity: 0;
        transform: translateX(50px);
    }
    100% {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes scaleUp {
    0% {
        opacity: 0;
        transform: scale(0);
    }
    100% {
        opacity: 1;
        transform: scale(1);
    }
}

@keyframes expandBorderCenter {
    0% {
        clip-path: inset(0 50% 0 50%);
        opacity: 0;
    }
    100% {
        clip-path: inset(0 0 0 0);
        opacity: 1;
    }
}

$bg-dark: #050505;
$bg-card: #000000;
$border-dark-red: #3a0e0e;
$accent-red: #e60000;
$hover-red: #ff1a1a;
$text-white: #ffffff;
$text-grey: #a0a0a0;

.overlay-modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.7);
    backdrop-filter: blur(8px);
    -webkit-backdrop-filter: blur(8px);
    z-index: 9999;
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal-container {
    background-color: $bg-dark;
    border: 1px solid $border-dark-red;
    border-radius: 16px;
    padding: 30px;
    width: 90%;
    max-width: 600px;
    color: $text-white;
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.8);
    animation: slideFromBottomContainer 0.6s cubic-bezier(0.25, 1, 0.5, 1) both;

    .modal-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;

        .title-group {
            display: flex;
            align-items: center;
            gap: 10px;
            animation: slideFromTop 0.5s ease-out 0.2s both;

            .icon-red {
                color: $accent-red;
                font-size: 28px;
            }

            h2 {
                margin: 0;
                font-size: 22px;
                font-weight: 700;
            }
        }

        .close-btn {
            color: $text-grey;
            cursor: pointer;
            transition: color 0.1s;
            animation: scaleUp 0.4s ease-out 0.3s both;

            &:hover {
                color: $text-white;
            }
        }
    }

    .dish-info {
        position: relative;
        padding-bottom: 20px;
        margin-bottom: 20px;

        &::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #1a1a1a;
            animation: expandBorderCenter 0.4s ease-out 1.2s both;
        }

        .dish-name {
            color: $accent-red;
            font-size: 18px;
            font-weight: 600;
            margin: 0 0 5px 0;
            animation: slideFromRight 0.5s ease-out 0.3s both;
        }

        .instruction {
            color: $text-grey;
            font-size: 14px;
            margin: 0;
            animation: slideFromRight 0.5s ease-out 0.4s both;
        }
    }

    .contenedor-ingredientes {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 15px;
        margin-bottom: 30px;

        @media (max-width: 480px) {
            grid-template-columns: 1fr;
        }
    }

    .opcion-ingrediente {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: $bg-card;
        border: 1px solid $border-dark-red;
        border-radius: 12px;
        padding: 16px 20px;
        cursor: pointer;
        transition: border-color 0.3s ease;

        &:nth-child(odd) {
            animation: slideFromLeft 0.5s ease-out both;
        }

        &:nth-child(even) {
            animation: slideFromRight 0.5s ease-out both;
        }

        @for $i from 1 through 30 {
            &:nth-child(#{$i}) {
                animation-delay: #{$i * 0.1s + 0.3s};
            }
        }

        .ingrediente-nombre {
            font-size: 15px;
            font-weight: 500;
        }

        input[type='checkbox'] {
            appearance: none;
            -webkit-appearance: none;
            width: 22px;
            height: 22px;
            border: 2px solid $border-dark-red;
            border-radius: 50%;
            background-color: transparent;
            cursor: pointer;
            outline: none;
            transition: all 0.2s ease-in-out;
        }

        &:hover {
            border-color: $hover-red;

            input[type='checkbox'] {
                border-color: $hover-red;
                transform: scale(1.15);
            }
        }

        input[type='checkbox']:checked {
            background-color: $accent-red;
            border-color: $accent-red;
        }
    }

    .modal-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: relative;
        padding-top: 20px;

        &::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 1px;
            background-color: #1a1a1a;
            animation: expandBorderCenter 0.4s ease-out 1.2s both;
        }

        .price-group {
            animation: slideFromLeft 0.5s ease-out 0.9s both;

            .price-label {
                color: $text-grey;
                font-size: 13px;
                margin: 0 0 4px 0;
            }

            .price-amount {
                color: $accent-red;
                font-size: 26px;
                font-weight: 700;
                margin: 0;
            }
        }

        .action-buttons {
            display: flex;
            gap: 12px;
            animation: slideFromRight 0.5s ease-out 0.9s both;

            @include mobile-mediano-down {
                flex-direction: column;
            }

            button {
                border: none;
                border-radius: 10px;
                padding: 12px 24px;
                font-size: 15px;
                cursor: pointer;
                transition:
                    background-color 0.3s ease,
                    transform 0.1s ease;

                &:active {
                    transform: scale(0.96);
                }
            }

            .btn-cancel {
                background-color: #1f1f1f;
                color: $text-white;
                font-weight: 500;

                &:hover {
                    background-color: #2e2e2e;
                }
            }

            .btn-add {
                background-color: $accent-red;
                color: $text-white;
                font-weight: 600;

                &:hover {
                    background-color: $hover-red;
                }
            }
        }
    }
}
</style>
