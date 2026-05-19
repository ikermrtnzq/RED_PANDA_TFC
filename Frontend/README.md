# Frontend - Restaurante Red Panda

Este proyecto es el frontend desarrollado con Vue 3 y Vite para la aplicación de Restaurante Red Panda.

## Configuración Recomendada del IDE

[VS Code](https://code.visualstudio.com/) + [Vue (Official)](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (y desactiva Vetur).

## Configuración Recomendada del Navegador

- Navegadores basados en Chromium (Chrome, Edge, Brave, etc.):
    - [Vue.js devtools](https://chromewebstore.google.com/detail/vuejs-devtools/nhdogjmejiglipccpnnnanhbledajbpd)
    - [Activa el Formateador de Objetos Personalizados en Chrome DevTools](http://bit.ly/object-formatters)
- Firefox:
    - [Vue.js devtools](https://addons.mozilla.org/en-US/firefox/addon/vue-js-devtools/)
    - [Activa el Formateador de Objetos Personalizados en Firefox DevTools](https://fxdx.dev/firefox-devtools-custom-object-formatters/)

## Soporte de Tipos para Importaciones `.vue` en TS

TypeScript no puede manejar información de tipos para importaciones `.vue` por defecto, así que reemplazamos el CLI `tsc` con `vue-tsc` para verificación de tipos. En editores, necesitamos [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) para que el servicio de lenguaje TypeScript sea consciente de los tipos `.vue`.

## Personalizar Configuración

Ver [Referencia de Configuración de Vite](https://vite.dev/config/).

## Configuración del Proyecto

```sh
npm install
```

### Compilar y Recargar en Caliente para Desarrollo

```sh
npm run dev
```

### Verificación de Tipos, Compilar y Minificar para Producción

```sh
npm run build
```

### Lint con [ESLint](https://eslint.org/)

```sh
npm run lint
```
