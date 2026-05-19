<template>
    <div class="contenedor-admin">
        <div class="panel-administracion">
            <h2 class="titulo-panel">{{ $t('administrador.titulo') }}</h2>

            <div class="opciones-admin">
                <div class="opcion-admin modificar-carta" @click="hacerScroll('.gestion-carta')">
                    {{ $t('administrador.opciones.modificar') }}
                </div>

                <div class="opcion-admin annadir" @click="hacerScroll('.formulario-adicion')">
                    {{ $t('administrador.opciones.annadir') }}
                </div>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
function hacerScroll(boton: string) {
    const elemento = document.querySelector(boton)
    if (elemento) {
        elemento.scrollIntoView({ behavior: 'smooth', block: 'start' })
    }
}
</script>

<style lang="scss" scoped>
.contenedor-admin {
    background-color: #000000;
    min-height: 50vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 60px 20px;

    .panel-administracion {
        width: 100%;
        max-width: 900px;
        text-align: center;

        .titulo-panel {
            color: $color-rojo-panda;
            font-size: 32px;
            font-weight: 700;
            margin-bottom: 40px;
        }

        .opciones-admin {
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;

            .opcion-admin {
                flex: 1;
                min-width: 300px;
                height: 150px;
                background-color: #000000;
                border: 1px solid $color-rojo-oscuro;
                border-radius: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                color: $color-rojo-panda;
                font-size: 18px;
                font-weight: 500;
                cursor: pointer;
                transition: all 0.3s ease;

                &:hover {
                    border: 1px solid $color-rojo-oscuro-claro;
                    transform: translateY(-5px);
                    background-color: $color-rojo-oscuro;
                    filter: brightness(1.1);
                    color: aliceblue;
                    box-shadow: 0 5px 15px rgba(67, 21, 23, 0.3);
                }

                &.modificar-carta {
                    border-bottom: 3px solid $color-rojo-oscuro;
                }

                &.annadir {
                    border-bottom: 3px solid $color-rojo-oscuro;
                }
            }
        }
    }
}
</style>
