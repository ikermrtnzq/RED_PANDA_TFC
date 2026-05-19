<template>
    <div class="contenedor-titulos">
        <h1>{{ $t('tienda.titulo') }}</h1>
        <h2>{{ $t('tienda.descripcion') }}</h2>
    </div>
    <div class="contenedor-buscador-platos">
        <img src="/assets/busqueda.webp" alt="buscar" />
        <input @input="guardarTexto" type="text" :placeholder="$t('tienda.buscador.buscar') + '...'" />
    </div>
    <div class="contenedor-categorias">
        <div class="nombre-categoria">
            <img src="/assets/filtrar.webp" alt="filtrar" />
            <p>{{ $t('tienda.buscador.categ') }}</p>
        </div>
        <div class="categorias">
            <CategoriasFiltro
                v-for="categoria in categorias"
                :key="categoria.id"
                :nombre="categoria.nombre"
                :slug="categoria.slug"
                @filtrar="cargarPlatos"
                :class="{ activo: categoriaActiva === categoria.slug }"
            />
        </div>
    </div>
    <div class="contenedor-platos">
        <TarjetaPlato
            v-for="plato in platosFiltrados"
            :key="`${plato.categoria_slug}-${plato.id}`"
            :nombre="$t(plato.nombre)"
            :descripcion="$t(plato.descripcion)"
            :precio="plato.precio"
            :imagen="plato.imagen"
            :categoria_slug="plato.categoria_slug"
            @configIngre="abrirConfigurador(plato)"
            @añadir="manejarAnnadir(plato, '', [])"
        />
    </div>
    <div>
        <Paginacion
            :offset="offset"
            :limite="noHayMasPlatos"
            @cargarMasPlatos="cargarPlatos(categoriaActiva, offset + 8)"
            @cargarMenosPlatos="cargarPlatos(categoriaActiva, offset - 8)"
        />
    </div>
    <div>
        <AlertaCarrito
            :visible="alerta.visible"
            :titulo="alerta.titulo"
            :mensaje="alerta.mensaje"
            :mostrar-boton="alerta.mostrarBoton"
            :tipo="alerta.tipo"
        />
        <TarjetaIngredientes
            v-if="platoConfigurando !== null"
            :nombre="platoConfigurando.nombre"
            :precio="platoConfigurando.precio"
            :ingredientes="ingredientes"
            @cerrar="platoConfigurando = null"
            @añadir="
                manejarAnnadir(
                    platoConfigurando,
                    $event.listaIngredientesQuitados,
                    $event.ingredientesSeleccionados,
                )
            "
        />
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, reactive, computed } from 'vue'
import { useI18n } from 'vue-i18n'
import { obtenerPlatos, obtenerCartaCompleta } from '@/services/Tienda/PlatosService'
import { obtenerCategorias } from '@/services/Tienda/CategoriasService'
import TarjetaPlato from '@/components/TiendaComp/TarjetaPlato.vue'
import CategoriasFiltro from '@/components/TiendaComp/CategoriasFiltro.vue'
import { useCarritoStore } from '@/stores/counter'
import AlertaCarrito from '@/components/AlertaComp/AlertaCarrito.vue'
import Paginacion from '@/components/TiendaComp/Paginacion.vue'
import TarjetaIngredientes from '@/components/TiendaComp/TarjetaIngredientes.vue'
import { obtenerIngredientes } from '@/services/Tienda/IngredientesService'
import { watch } from 'vue'
import { useUserStore } from '@/stores/userStore'

interface Plato {
    id: number
    nombre: string
    precio: number
    imagen: string
    descripcion: string
    categoria_slug: string
}

interface Categoria {
    id: number
    nombre: string
    slug: string
}

const platos = ref<Plato[]>([])
const categorias = ref<Categoria[]>([])
const ingredientes = ref([])
const categoriaActiva = ref<string>('todos')
const carrito = useCarritoStore()
const { añadirProducto } = carrito
const { t } = useI18n()
const platoConfigurando = ref<Plato | null>(null)
const userStore = useUserStore();

const alerta = reactive({
    visible: false,
    titulo: '',
    mensaje: '',
    mostrarBoton: false,
    tipo: 1,
})

const lanzarAlerta = (nombreProducto: String, tipo: Number) => {
    alerta.visible = true

    tipo === 1 ? (alerta.tipo = 1) : 0

    alerta.titulo = t('alertas.annadir.titulo')
    alerta.mensaje = `${nombreProducto} ${t('alertas.annadir.mensaje')}`
    alerta.mostrarBoton = true

    setTimeout(() => {
        alerta.visible = false
    }, 5000)
}

//Para la paginacion
const offset = ref(0) // Este es el inicio
//Para controlar que hemos llegado al final de la lista
const noHayMasPlatos = ref(false)

const cargarPlatos = async (cat?: string, nuevoOffset?: number) => {
    //Si cambiamos de categoria, reseteamos el offset
    if (cat && cat !== categoriaActiva.value) {
        offset.value = 0
        noHayMasPlatos.value = false
    } else if (nuevoOffset !== undefined) {
        offset.value = nuevoOffset
    }

    categoriaActiva.value = cat || 'todos'

    if (cat === 'todos') {
        const carta = await obtenerPlatos(undefined, offset.value)
        if (carta.length < 8) {
            noHayMasPlatos.value = true
        } else {
            noHayMasPlatos.value = false
        }
        platos.value = carta
    } else {
        const carta = await obtenerPlatos(cat, offset.value)
        if (carta.length < 8) {
            noHayMasPlatos.value = true
        } else {
            noHayMasPlatos.value = false
        }
        platos.value = carta
    }
}

//Añade plato recibe plato y los ingredientes quitados, y los manda al store para añadirlos al carrito
const manejarAnnadir = (plato: Plato, ingredientesQuitados: string, ingredientesId: number[]) => {
    platoConfigurando.value = null
    añadirProducto(plato, ingredientesQuitados, ingredientesId)
    lanzarAlerta(plato.nombre, 1)
}

//Para abrir el configurador de ingredientes
const abrirConfigurador = async (plato: Plato) => {
    platoConfigurando.value = plato
    ingredientes.value = await obtenerIngredientes(plato.id, plato.categoria_slug)
}

const textoBusqueda = ref('');
const cartaCompleta = ref<Plato[]>([]);

const guardarTexto = async (event : Event) => {
    const texto = event.target as HTMLInputElement;
    textoBusqueda.value = texto.value;

    //Comprobamos que el usuario ha buscado algo y todavia no hemos obtenido la carta completa
    if(textoBusqueda.value.length > 0 && cartaCompleta.value.length === 0){
        cartaCompleta.value = await obtenerCartaCompleta();
    }
}

const platosFiltrados = computed (() => {
    const texto = textoBusqueda.value.toLocaleLowerCase().trim();

    if(!texto){
        return platos.value
    }

    //obtenemos la carta entera 
    return cartaCompleta.value.filter((plato) => {
        const nombre = plato.nombre.toLocaleLowerCase();
        const descripcion = plato.descripcion.toLocaleLowerCase();

        return nombre.includes(texto) || descripcion.includes(texto);
    })
});
watch(() =>userStore.prefenciaIdioma , async (nuevoIdioma) => {
    console.log("🔄 El idioma ha cambiado a:", nuevoIdioma, "traduciendo tienda...");
    // Resetear carta completa para que se fuerce la traducción también en el buscador
    cartaCompleta.value = [];
    // Recargamos los platos: el servicio detectará el nuevo idioma y llamará a la API
    await cargarPlatos(categoriaActiva.value, offset.value);
});


onMounted(async () => {
    platos.value = await obtenerPlatos()
    categorias.value = await obtenerCategorias()
    console.log(ingredientes.value)
})
</script>

<style lang="scss" scoped>
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

@keyframes slideFromBottom {
    0% {
        opacity: 0;
        transform: translateY(50px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@keyframes inflateScroll {
    0% {
        opacity: 0;
        transform: scale(0);
    }
    100% {
        opacity: 1;
        transform: scale(1);
    }
}

.contenedor-titulos {
    text-align: center;
    margin-top: 40px;
    animation: slideFromRight 0.8s ease-out both;
    h2 {
        color: $color-blanco-sucio;
        font-size: clamp(16px, 2vw + 10px, 30px);
        font-weight: 400;
        margin-top: 13px;
    }
    h1 {
        font-size: clamp(24px, 4vw + 10px, 50px);
        color: $color-texto-blanco;
        font-weight: 700;
    }
}

.contenedor-buscador-platos {
    display: flex;
    align-items: center;
    border: 1px solid $color-rojo-oscuro;
    border-radius: 20px;
    width: clamp(250px, 90%, 800px);
    margin: 40px auto;
    gap: 10px;
    background-color: $color-fondo-header;
    animation: slideFromLeft 0.8s ease-out both;
    input {
        border: none;
        background: none;
        font-size: 13px;
        width: 100%;
        height: 100%;
        color: $color-texto-blanco;
        padding: 18px 18px 18px 0px;
        &:focus {
            outline: none;
        }
    }
    img {
        padding-left: 18px;
    }
    &:focus-within {
        border: 1px solid $color-rojo-panda;
    }
}

p {
    color: $color-texto-blanco;
}

.contenedor-categorias {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 8px;
    font-size: 13px;
    font-weight: 600;
    .nombre-categoria {
        display: flex;
        gap: 10px;
        animation: slideFromLeft 0.8s ease-out both;
    }
    .categorias {
        max-width: 1600px;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
        margin-top: 20px;

        :deep(> *) {
            animation: slideFromBottom 0.6s ease-out both;
        }

        :deep(> *) {
            scale: 1.02;
        }

        @for $i from 1 through 20 {
            :deep(> *:nth-child(#{$i})) {
                animation-delay: #{$i * 0.1s};
            }
        }

        .activo {
            background-color: $color-rojo-panda;
            border-color: $color-rojo-panda;
            color: white;
        }
    }
}

.contenedor-platos {
    display: flex;
    gap: 30px;
    justify-content: center;
    flex-wrap: wrap;
    max-width: 1600px;
    margin: 50px auto;
    :deep(> *) {
        transform-origin: center;
        animation: inflateScroll linear both;
        animation-timeline: view();
        animation-range: entry 5% cover 25%;
    }
}
</style>
