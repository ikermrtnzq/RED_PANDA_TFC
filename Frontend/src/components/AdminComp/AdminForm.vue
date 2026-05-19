<script setup lang="ts">
  import { onMounted, ref } from 'vue';
  import { annadirPlato } from '@/services/Tienda/PlatosService';
  import { obtenerCategorias } from '@/services/Tienda/CategoriasService';
  import { obtenerIngredientes, obtenerTodosIngredientes } from '@/services/Tienda/IngredientesService';

  //Variables que usamos para recoger los datos del formulario de creacion
  const categoria = ref("");
  const nombre = ref("");
  const descripcion = ref("");
  const precio = ref<number>(0);
  const cantidad = ref(1);
  const img = ref("");
  const ingredientes = ref<Ingrediente[]>([]);

  interface Ingrediente {
    id: number;
    nombre: string;
  }

  //mensaje 
  const emit = defineEmits(['actualizar-tabla']);
  
  //Para crear 
  const enviarDatos = async () => {
    const res = await annadirPlato(nombre.value, descripcion.value, precio.value, cantidad.value, categoria.value, img.value, ingredientes.value);

    if(res) {
      nombre.value = "";
      descripcion.value = "";
      precio.value = 0;
      cantidad.value = 0;
      img.value = "";
      ingredientes.value = [];

      //Avisamos al padre que necesita actualizar la carta
      emit('actualizar-tabla');
    }else{
      alert("ERROR");
    }

    
  }

  const listaCategorias = ref<any[]>([]);
  const listaIngredientes = ref<any[]>([])

  onMounted (async () => {
    const resultado = await obtenerCategorias();
    const resultado2 = await obtenerTodosIngredientes();
    if(resultado){
      //filtro para que la categoria todos no aparezca en el desplegable
      listaCategorias.value = resultado.filter((cat: any) =>cat.slug !== 'todos');
      listaIngredientes.value = resultado2;
    }
  })

</script>
<template>
  <div class="contenedor-admin">
    <div class="formulario-adicion">
      <h2 class="titulo-panel">{{ $t('administrador.annadir.titulo') }}</h2>
      
      <div class="seleccion-elemento">
          <label for="entidad">
            {{ $t('administrador.annadir.seleccionar-elemento') }}
          </label>

          <select id="entidad" v-model="categoria">
            <option value="" disabled selected>
              {{ $t('administrador.annadir.placeholder-elemento') }}
            </option>

            <option 
              v-for="cat in listaCategorias"
              :key="cat.id"
              :value="cat.slug"
            >
              {{ $t(cat.nombre) }}
            </option>
          </select>
      </div>

      <div class="introduccion-datos">

        <div class="campo">
          <label for="nombre">
            {{ $t('administrador.annadir.nombre-label') }}
          </label>

          <input
            v-model="nombre"
            id="nombre"
            type="text"
            :placeholder="$t('administrador.annadir.nombre-placeholder')"
          >
        </div>
        
        <div class="campo">
          <label for="descripcion">
            {{ $t('administrador.annadir.descripcion-label') }}
          </label>

          <input
            v-model="descripcion"
            id="descripcion"
            type="text"
            :placeholder="$t('administrador.annadir.descripcion-placeholder')"
          >
        </div>

        <div class="campo">
          <label for="precio">
            {{ $t('administrador.annadir.precio-label') }}
          </label>

          <input
            v-model.number="precio"
            id="precio"
            type="text"
            :placeholder="$t('administrador.annadir.precio-placeholder')"
          >
        </div>

        <div class="campo">
          <label for="cantidad">
            {{ $t('administrador.annadir.cantidad-label') }}
          </label>

          <input
            v-model.number="cantidad"
            id="cantidad"
            type="number"
            :placeholder="$t('administrador.annadir.cantidad-placeholder')"
          >
        </div>

        <div class="campo">
          <label for="img">
            {{ $t('administrador.annadir.imagen-label') }}
          </label>

          <input
            v-model="img"
            id="img"
            type="text"
            :placeholder="$t('administrador.annadir.imagen-placeholder')"
          >
        </div>

        <div class="ingredientes">
          <h3>{{ $t('administrador.annadir.ingredientes') }}</h3>

          <div v-for="ing in listaIngredientes" :key="ing.id">
            <input
              type="checkbox"
              :id="ing.id"
              :value="ing"
              v-model="ingredientes"
              class="ingrediente"
            >

            <label :for="ing.id">{{ ing.nombre }}</label>
          </div>
        </div>

        <button
          @click="enviarDatos"
          type="button"
          class="boton-enviar"
        >
          {{ $t('administrador.annadir.boton') }}
        </button>

      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.contenedor-admin {
  background-color: $color-fondo-tarjeta;
  min-height: 60vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 20px;

  .formulario-adicion {
    scroll-margin-top: 100px;
    width: 100%;
    max-width: 600px;
    background-color: $color-fondo-tarjeta;
    padding: 40px;
    border-radius: 20px;
    border: 1px solid $color-rojo-oscuro;
    border-bottom: 4px solid $color-rojo-panda;
    text-align: center;
    box-shadow: 0 10px 30px $color-fondo-tarjeta;

    .titulo-panel {
      color: $color-rojo-panda;
      font-size: 24px;
      font-weight: 700;
      margin-bottom: 30px;
      text-transform: uppercase;
    }

    .seleccion-elemento {
      margin-bottom: 30px;
      display: flex;
      flex-direction: column;
      gap: 10px;
      align-items: center;

      label {
        color: $color-rojo-claro;
        font-weight: 600;
      }
    }

    .introduccion-datos {
      display: flex;
      flex-direction: column;
      gap: 20px;

      .campo {
        display: flex;
        justify-content: space-between;
        align-items: center;
        gap: 15px;

        label {
          flex: 1;
          text-align: right;
          color: $color-texto-blanco;
          font-size: 14px;
        }

        input {
          flex: 1.5;
        }
      }
    }

    input, select {
      padding: 12px 15px;
      background-color: $color-fondo-controles;
      border: 1px solid $color-rojo-oscuro-claro;
      border-radius: 10px;
      color: $color-texto-blanco;
      outline: none;
      transition: all 0.3s ease;

      &:focus {
        border-color: $color-rojo-panda;
        box-shadow: 0 0 8px rgba($color-rojo-panda, 0.2);
      }
    }

    .ingredientes {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      gap: 10px;
      margin-top: 10px;
      padding: 15px;
      background-color: $color-fondo-tarjeta;
      border-radius: 12px;
      border: 1px inset $color-fondo-controles;

      div {
        position: relative;

        .ingrediente {
          position: absolute;
          opacity: 0;
          cursor: pointer;
          height: 0;
          width: 0;

  
          &:checked + label {
            background-color: $color-rojo-panda;
            border-color: $color-rojo-panda;
            transform: scale(1.05);
          }
        }

        label {
          display: inline-block;
          padding: 8px 16px;
          border: 1px solid $color-rojo-oscuro-claro;
          border-radius: 20px;
          color: $color-texto-blanco;
          font-size: 13px;
          font-weight: 600;
          cursor: pointer;
          transition: all 0.2s ease;
          background-color: $color-fondo-controles;

          &:hover {
            border-color: $color-rojo-claro;
            color: $color-texto-blanco;
          }
        }
      }
    }

    .boton-enviar {
      margin-top: 20px;
      padding: 15px;
      background-color: $color-rojo-oscuro;
      color: #ffffff;
      border: 1px solid $color-rojo-panda;
      border-radius: 10px;
      font-weight: 700;
      cursor: pointer;
      transition: all 0.3s ease;

      &:hover {
        background-color: $color-rojo-panda;
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba($color-rojo-panda, 0.3);
      }
    }
  }
}

option {
  background-color: #121212;
  color: #ffffff;
}
</style>